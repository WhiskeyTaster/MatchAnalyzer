package com.whiskeytaster.matchanalyzer.service;

import com.whiskeytaster.matchanalyzer.driver.Driver;
import com.whiskeytaster.matchanalyzer.exception.InvalidEventsNumberException;
import com.whiskeytaster.matchanalyzer.model.Competitor;
import com.whiskeytaster.matchanalyzer.model.Event;
import com.whiskeytaster.matchanalyzer.model.EventStorage;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EventService {
    private EventStorage eventStorage;
    private final Set<String> uniqueTeamNames = new HashSet<>();
    private final Driver driver;

    public List<String> getTeamNamesAlphabetically() {
        if (uniqueTeamNames.size() == 0)
            loadUniqueTeamNames();
        return uniqueTeamNames.stream()
                .sorted(String::compareTo)
                .toList();
    }

    public String stringEvent(final @NotNull Event event) {
        StringBuilder builder = new StringBuilder();
        String startDateString = "Start date: " +
                event.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ",\n";

        String competitorsString = event.getHomeTeam().getName() + "(" + event.getHomeTeam().getCountry() + ") " +
                "vs. " +
                event.getAwayTeam().getName() + "(" + event.getAwayTeam().getCountry() + "), \n";

        String venueString = "Venue: ";
        if (event.getVenue() == null)
            venueString += "no data, \n";
        else
            venueString += event.getVenue().getName() + ", \n";

        String mostProbableResultString = "Highest probable result: " + getEventMostProbableResult(event) +
                " (" + getMaxEventProbability(event) + ") \n";


        builder.append(startDateString)
                .append(competitorsString)
                .append(venueString)
                .append(mostProbableResultString);


        return builder.toString();
    }

    public List<String> getEventsStringList() {
        return eventStorage.getEvents()
                .stream()
                .map(this::stringEvent)
                .toList();
    }

    public List<String> getMostProbableResultsAsString(int numOfEvents) throws InvalidEventsNumberException {
        return getMostProbableResults(numOfEvents).stream()
                .map(this::stringEvent)
                .toList();
    }

    public List<Event> getMostProbableResults(int numOfEvents) throws InvalidEventsNumberException {
        if (numOfEvents < 1)
            throw new InvalidEventsNumberException("Requested number of events is less than 1. ");

        return eventStorage.getEvents()
                .stream()
                .sorted((a, b) -> Double.compare(getMaxEventProbability(b), getMaxEventProbability(a)))
                .limit(numOfEvents)
                .toList();
    }

    public void loadDataFromFile(String fileName) {
        eventStorage = driver.readEventsFromFile(fileName);
    }

    private @NotNull String getEventMostProbableResult(final @NotNull Event event) {
        double maxProbability = getMaxEventProbability(event);
        String result = "";

        if (Double.compare(maxProbability, event.getProbabilityHomeTeamWinner()) == 0)
            result += "HOME_TEAM_WIN";
        else if (Double.compare(maxProbability, event.getProbabilityDraw()) == 0)
            result += "DRAW";
        else
            result += "AWAY_TEAM_WIN";
        return result;
    }

    private double getMaxEventProbability(final @NotNull Event event) {
        return Double.max(event.getProbabilityHomeTeamWinner(),
                Double.max(event.getProbabilityDraw(), event.getProbabilityAwayTeamWinner()));
    }

    private void loadUniqueTeamNames() {
        uniqueTeamNames.addAll(
                eventStorage.getEvents()
                        .stream()
                        .map(Event::getHomeTeam)
                        .map(Competitor::getName)
                        .collect(Collectors.toSet())
        );

        uniqueTeamNames.addAll(
                eventStorage.getEvents().stream()
                        .map(Event::getAwayTeam)
                        .map(Competitor::getName)
                        .collect(Collectors.toSet())
        );
    }
}

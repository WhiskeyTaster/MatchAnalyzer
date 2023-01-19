package com.whiskeytaster.matchanalyzer.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiskeytaster.matchanalyzer.model.Competitor;
import com.whiskeytaster.matchanalyzer.model.Event;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventService {
    @JsonProperty("Events")
    private final ArrayList<Event> events = new ArrayList<>();
    private final Set<String> uniqueTeamNames = new HashSet<>();

    private double getMaxEventProbability(final @NotNull Event event) {
        return Double.max(event.getProbabilityHomeTeamWinner(),
                Double.max(event.getProbabilityDraw(), event.getProbabilityAwayTeamWinner()));
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

    private void printEvent(final @NotNull Event event) {
        StringBuilder builder = new StringBuilder();
        String startDateString = "Start date: " +
                event.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ",\n";

        String competitorsString = event.getHomeTeam().getName() + "(" + event.getHomeTeam().getCountry() + ") " +
                "vs. " +
                event.getAwayTeam().getName() + "(" + event.getAwayTeam().getCountry() + "), \n";

        String venueString = "Venue: ";
        if (event.getVenue() == null)
            venueString += "brak \n";
        else
            venueString += event.getVenue().getName() + ", \n";

        String mostProbableResultString = "Highest probable result: " + getEventMostProbableResult(event) +
                " (" + getMaxEventProbability(event) + ") \n";


        builder.append(startDateString)
                .append(competitorsString)
                .append(venueString)
                .append(mostProbableResultString);


        System.out.println(builder);
    }

    public void printAll() {
        events.stream().forEach(this::printEvent);
    }

    public void printMostProbable(int numOfEvents) {
        events.stream()
                .sorted((a, b) -> Double.compare(getMaxEventProbability(b), getMaxEventProbability(a)))
                .limit(numOfEvents)
                .forEach(this::printEvent);
    }

    public List<Event> getMostProbableResults(int numOfEvents) {
        if (numOfEvents < 1)
            return new ArrayList<>();

        return events.stream()
                .sorted((a, b) -> Double.compare(getMaxEventProbability(b), getMaxEventProbability(a)))
                .limit(numOfEvents)
                .collect(Collectors.toList());
    }

    public List<Event> getEvents() {
        return events;
    }

    public void loadUniqueTeamNames() {
        uniqueTeamNames.addAll(
                events.stream()
                        .map(Event::getHomeTeam)
                        .map(Competitor::getName)
                        .collect(Collectors.toSet())
        );

        uniqueTeamNames.addAll(
                events.stream()
                        .map(Event::getAwayTeam)
                        .map(Competitor::getName)
                        .collect(Collectors.toSet())
        );
    }

    public void printTeamNamesAlphabetically() {
        uniqueTeamNames.stream()
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}

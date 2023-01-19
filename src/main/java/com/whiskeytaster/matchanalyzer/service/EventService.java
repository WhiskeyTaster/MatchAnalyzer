package com.whiskeytaster.matchanalyzer.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiskeytaster.matchanalyzer.model.Event;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventService {
    @JsonProperty("Events")
    private ArrayList<Event> events;

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

}

package com.whiskeytaster.matchanalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Event {
    @JsonProperty("sport_event_id")
    private String sportEventId;
    @JsonProperty("start_date")
    private OffsetDateTime startDate;
    @JsonProperty("sport_name")
    private String sportName;
    @JsonProperty("competition_name")
    private String competitionName;
    @JsonProperty("competition_id")
    private String competitionId;
    @JsonProperty("season_name")
    private String seasonName;
    private Competitor[] competitors = new Competitor[2];
    private Venue venue;
    @JsonProperty("probability_home_team_winner")
    private double probabilityHomeTeamWinner;
    @JsonProperty("probability_draw")
    private double probabilityDraw;
    @JsonProperty("probability_away_team_winner")
    private double probabilityAwayTeamWinner;

    public Competitor getHomeTeam() {
        return competitors[0];
    }

    public Competitor getAwayTeam() {
        return competitors[1];
    }

    public void setStartDate(final String stringDate) {
        this.startDate = OffsetDateTime.parse(stringDate);
    }
}

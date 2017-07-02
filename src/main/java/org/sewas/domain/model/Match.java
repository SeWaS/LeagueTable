package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
        setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class Match {

    @JsonProperty("MatchID")
    private int MatchID;

    @JsonProperty("Goals")
    private List<Goal> Goal;

    @JsonProperty("Team1")
    private Team Team1;

    @JsonProperty("Team2")
    private Team Team2;

    @JsonProperty("NumberOfViewers")
    private int NumberOfViewers;

    @JsonProperty("Group")
    private Group Group;

    @JsonProperty("MatchResults")
    private List<MatchResult> matchResults;

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }

    public List<org.sewas.domain.model.Goal> getGoal() {
        return Goal;
    }

    public void setGoal(List<org.sewas.domain.model.Goal> goal) {
        Goal = goal;
    }

    public Team getTeam1() {
        return Team1;
    }

    public void setTeam1(Team team1) {
        Team1 = team1;
    }

    public Team getTeam2() {
        return Team2;
    }

    public void setTeam2(Team team2) {
        Team2 = team2;
    }

    public int getNumberOfViewers() {
        return NumberOfViewers;
    }

    public void setNumberOfViewers(int numberOfViewers) {
        NumberOfViewers = numberOfViewers;
    }

    public org.sewas.domain.model.Group getGroup() {
        return Group;
    }

    public void setGroup(org.sewas.domain.model.Group group) {
        Group = group;
    }

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }

    public void setMatchResults(List<MatchResult> matchResults) {
        this.matchResults = matchResults;
    }
}

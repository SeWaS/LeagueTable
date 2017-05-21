package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    private int MatchID;
    private boolean MatchIsFinished;
    private int LeagueId;
    private List<Goal> Goals;
    private Group Group;
    private List<MatchResult> MatchResults;
    private int NumberOfViewers;
    private Team Team1;
    private Team Team2;

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }

    public boolean isMatchIsFinished() {
        return MatchIsFinished;
    }

    public void setMatchIsFinished(boolean matchIsFinished) {
        MatchIsFinished = matchIsFinished;
    }

    public int getLeagueId() {
        return LeagueId;
    }

    public void setLeagueId(int leagueId) {
        LeagueId = leagueId;
    }

    public List<Goal> getGoals() {
        return Goals;
    }

    public void setGoals(List<Goal> goals) {
        Goals = goals;
    }

    public org.sewas.domain.model.Group getGroup() {
        return Group;
    }

    public void setGroup(org.sewas.domain.model.Group group) {
        Group = group;
    }

    public List<MatchResult> getMatchResults() {
        return MatchResults;
    }

    public void setMatchResults(List<MatchResult> matchResults) {
        MatchResults = matchResults;
    }

    public int getNumberOfViewers() {
        return NumberOfViewers;
    }

    public void setNumberOfViewers(int numberOfViewers) {
        NumberOfViewers = numberOfViewers;
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
}

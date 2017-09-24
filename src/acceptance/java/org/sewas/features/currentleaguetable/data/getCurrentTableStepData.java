package org.sewas.features.currentleaguetable.data;

import org.sewas.domain.model.LeagueTable;
import org.sewas.domain.model.Match;
import org.sewas.domain.model.TeamPosition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 22/05/17.
 */
@Component
public class getCurrentTableStepData {

    private LeagueTable leagueTable = new LeagueTable();
    private ResponseEntity<LeagueTable> response;
    private ResponseEntity<LeagueTable> responseForMatchdayTable;
    private List<Match> playedMatches = new ArrayList<Match>();
    private List<Match[]> playedMatchDays = new ArrayList<Match[]>();

    public ResponseEntity<LeagueTable> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<LeagueTable> response) {
        this.response = response;
    }

    public void setLeagueId(String leagueID) {
        this.leagueTable.setLeagueID(leagueID);
    }

    public String getLeagueID()
    {
        return this.leagueTable.getLeagueID();
    }

    public List<TeamPosition> getTable()
    {
        return this.leagueTable.getTable();
    }

    public void addMatch(Match match)
    {
        this.playedMatches.add(match);
    }

    public List<Match> getMatches()
    {
        return this.playedMatches;
    }

    public void resetMatches(){
        this.playedMatches.clear();
    }

    public void addMatchDay(Match[] matchday) {
        this.playedMatchDays.add(matchday);
    }

    public void setFetchForMatchdayResponse(ResponseEntity<LeagueTable> response) {
        this.responseForMatchdayTable = response;
    }

    public List<Match[]> getPlayedMatchDays() {
        return playedMatchDays;
    }

    public ResponseEntity<LeagueTable> getResponseForMatchdayTable() {
        return responseForMatchdayTable;
    }

    public void resetMatchdays() {
        this.playedMatchDays.clear();
    }
}

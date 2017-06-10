package org.sewas.features.util;

import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.Match;
import org.sewas.domain.model.model.Team;
import org.sewas.domain.model.model.TeamPosition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 22/05/17.
 */
@Component
public class World {

    private LeagueTable leagueTable = new LeagueTable();
    private ResponseEntity<LeagueTable> response;
    private List<Match> playedMatches = new ArrayList<Match>();

    public ResponseEntity<LeagueTable> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<LeagueTable> response) {
        this.response = response;
    }

    public void addTeams(List<String> teams) {
        for (String teamname : teams) {
            TeamPosition tp = new TeamPosition();

            Team t = new Team();
            t.TeamName = teamname;

            tp.setTeam(t);
        }
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
}

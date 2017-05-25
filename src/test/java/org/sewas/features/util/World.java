package org.sewas.features.util;

import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sebastian on 22/05/17.
 */
@Component
public class World {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private LeagueTable leagueTable = new LeagueTable();
    private ResponseEntity<LeagueTable> response;

    public void setTableResponseEntity() {
        this.response = this.testRestTemplate.getForEntity("/api/leagueTable/"+this.leagueTable.getLeagueID(), LeagueTable.class);
    }

    public ResponseEntity<LeagueTable> getResponse() {
        return response;
    }

    public void addTeams(List<String> teams) {
        for (String teamname : teams) {
            Team t = new Team();
            t.TeamName = teamname;
            this.leagueTable.addTeam(t);
        }
    }

    public void setLeagueId(String leagueID) {
        this.leagueTable.setLeagueID(leagueID);
    }
}

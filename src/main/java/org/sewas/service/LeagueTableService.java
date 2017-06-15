package org.sewas.service;

import org.sewas.client.OpenLigaDBClient;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.Match;
import org.sewas.domain.model.model.TeamPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sebastian on 21/05/17.
 */
@Service
public class LeagueTableService {

    @Autowired
    private OpenLigaDBClient openLigaDBClient;

    public LeagueTable returnCurrentLeagueTable(String leagueID){

        MatchDTO matchesDTO = this.openLigaDBClient.getMatchesForLeague(leagueID);

        LeagueTable lt = new LeagueTable();
        lt.setLeagueID(leagueID);

        for (Match match : matchesDTO.getMatches())
        {
            TeamPosition tp1 = findTeamPositionByTeamName(match.Team1.TeamName, lt);
            TeamPosition tp2 = findTeamPositionByTeamName(match.Team2.TeamName, lt);

            if(tp1 == null) {
                tp1 = new TeamPosition();
                tp1.setTeam(match.Team1);
                lt.addTeamPosition(tp1);
            }

            if(tp2 == null) {
                tp2 = new TeamPosition();
                tp2.setTeam(match.Team2);
                lt.addTeamPosition(tp2);
            }

            if(match.matchResults.get(1).PointsTeam1 > match.matchResults.get(1).PointsTeam2)
            {
                tp1.addVictory();
            }

            if(match.matchResults.get(1).PointsTeam1 == match.matchResults.get(1).PointsTeam2)
            {
                tp1.addTie();
                tp2.addTie();
            }

            if(match.matchResults.get(1).PointsTeam1 < match.matchResults.get(1).PointsTeam2)
            {
                tp2.addVictory();
            }

            lt.updatePoints(tp1);
            lt.updatePoints(tp2);
        }

        lt.sortTableByPoints();

        return lt;

    }

    private TeamPosition findTeamPositionByTeamName(String teamName, LeagueTable leagueTable) {
        for(TeamPosition t : leagueTable.getTable()) {
            if(t.getTeam().TeamName.equals(teamName)) {
                return t;
            }
        }
        return null;
    }

}

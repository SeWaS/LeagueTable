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

            TeamPosition tp1 = new TeamPosition();
            tp1.setTeam(match.Team1);

            TeamPosition tp2 = new TeamPosition();
            tp2.setTeam(match.Team2);

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

            lt.addTeamPosition(tp1);
            lt.addTeamPosition(tp2);
        }

        lt.sortTableByPoints();

        return lt;

    }

}

package org.sewas.service;

import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.LeagueTable;
import org.sewas.domain.model.Match;
import org.sewas.domain.model.TeamPosition;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.rest.dto.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 21/05/17.
 */
@Service
public class LeagueTableService {

    @Autowired
    private OpenLigaDBClient openLigaDBClient;

    public LeagueTable returnCurrentLeagueTable(String leagueID, String season) throws SeasonNotAvailableException, OpenLigaDbNotOkException {

        MatchDTO matchesDTO = this.openLigaDBClient.getMatchesForLeagueMatchday(leagueID, season);

        LeagueTable lt = calculateTableForGivenMatches(leagueID, matchesDTO.getMatches());

        lt.sortTableByPoints();

        return lt;

    }

    public LeagueTable returnMatchdayLeagueTable(String leagueID, String season, String matchday) throws MatchdayNotAvailableException, OpenLigaDbNotOkException {

        Integer m;

        try {
            m = Integer.valueOf(matchday);
        } catch (Exception e) {
            throw new MatchdayNotAvailableException();
        }

        List<Match> matches = new ArrayList<>();

        for(Integer i=1;i<=m;i++) {

            try {
                MatchDTO tempMatchDTO = this.openLigaDBClient.getMatchesForLeagueMatchday(leagueID, season, i.toString());
                matches.addAll(tempMatchDTO.getMatches());
            } catch (MatchdayNotAvailableException me) {
                matches.clear();
                throw new MatchdayNotAvailableException();
            }

        }

        LeagueTable lt = calculateTableForGivenMatches(leagueID, matches);

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

    private LeagueTable calculateTableForGivenMatches(String leagueID, List<Match> matches) {

        LeagueTable lt = new LeagueTable();
        lt.setLeagueID(leagueID);

        for (Match match : matches)
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
                tp2.addLoss();
            }

            if(match.matchResults.get(1).PointsTeam1 == match.matchResults.get(1).PointsTeam2)
            {
                tp1.addTie();
                tp2.addTie();
            }

            if(match.matchResults.get(1).PointsTeam1 < match.matchResults.get(1).PointsTeam2)
            {
                tp2.addVictory();
                tp1.addLoss();
            }

            tp1.addGoalsFor(match.matchResults.get(1).PointsTeam1);
            tp1.addGoalsAgainst(match.matchResults.get(1).PointsTeam2);
            tp2.addGoalsFor(match.matchResults.get(1).PointsTeam2);
            tp2.addGoalsAgainst(match.matchResults.get(1).PointsTeam1);

            //lt.updatePoints(tp1);
            //lt.updatePoints(tp2);
        }

        return lt;
    }
}

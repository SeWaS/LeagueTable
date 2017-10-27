package org.sewas.service;

import org.sewas.OpenLigaDBConfig;
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

    private OpenLigaDBClient openLigaDBClient;

    @Autowired
    public LeagueTableService(OpenLigaDBClient openLigaDBClient) {
        this.openLigaDBClient = openLigaDBClient;
    }

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
            if(t.getTeam().getTeamName().equals(teamName)) {
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

            if(!match.getMatchIsFinished()) {
                break;
            }

            TeamPosition tp1 = findTeamPositionByTeamName(match.getTeam1().getTeamName(), lt);
            TeamPosition tp2 = findTeamPositionByTeamName(match.getTeam2().getTeamName(), lt);

            if(tp1 == null) {
                tp1 = new TeamPosition();
                tp1.setTeam(match.getTeam1());
                lt.addTeamPosition(tp1);
            }

            if(tp2 == null) {
                tp2 = new TeamPosition();
                tp2.setTeam(match.getTeam2());
                lt.addTeamPosition(tp2);
            }

            if(match.getMatchResults().get(1).getPointsTeam1() > match.getMatchResults().get(1).getPointsTeam2())
            {
                tp1.addVictory();
                tp2.addLoss();
            }

            if(match.getMatchResults().get(1).getPointsTeam1() == match.getMatchResults().get(1).getPointsTeam2())
            {
                tp1.addTie();
                tp2.addTie();
            }

            if(match.getMatchResults().get(1).getPointsTeam1() < match.getMatchResults().get(1).getPointsTeam2())
            {
                tp2.addVictory();
                tp1.addLoss();
            }

            tp1.addGoalsFor(match.getMatchResults().get(1).getPointsTeam1());
            tp1.addGoalsAgainst(match.getMatchResults().get(1).getPointsTeam2());
            tp2.addGoalsFor(match.getMatchResults().get(1).getPointsTeam2());
            tp2.addGoalsAgainst(match.getMatchResults().get(1).getPointsTeam1());
        }

        return lt;
    }
}

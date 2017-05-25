package service;

import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.dto.MatchDTO;
import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.Match;
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

        MatchDTO matches = this.openLigaDBClient.getMatchesForLeague(leagueID);

        return new LeagueTable();

    }

}

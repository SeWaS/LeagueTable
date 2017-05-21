package org.sewas.service;

import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.LeagueTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sebastian on 21/05/17.
 */
@Service
public class LeagueTableService {

    @Autowired
    private OpenLigaDBClient openLigaDBClient;

    public LeagueTable returnCurrentLeagueTable(String leagueID){
        Boolean result = this.openLigaDBClient.getMatchesForLeague(leagueID);
        return new LeagueTable();
    }

}

package org.sewas.client;

import org.sewas.domain.model.LeagueTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sebastian on 21/05/17.
 */
@Component
public class OpenLigaDBClient {

    private RestTemplate restTemplate;

    @Autowired
    public OpenLigaDBClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    private String baseUrl = "https://www.openligadb.de/api/getmatchdata/";

    public Boolean getMatchesForLeague(String leagueID){
        this.restTemplate.getForEntity(this.baseUrl + leagueID, String.class);
        return true;
    }

}

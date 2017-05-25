package org.sewas.client;

import org.sewas.domain.model.dto.MatchDTO;
import org.sewas.domain.model.model.Match;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

/**
 * Created by sebastian on 21/05/17.
 */
@Component
public class OpenLigaDBClient {

    private RestTemplate restTemplate = new RestTemplate();

    private String baseUrl = "https://www.openligadb.de/api/getmatchdata/";

    public MatchDTO getMatchesForLeague(String leagueID){

        //ResponseEntity<Match[]> Matches = restTemplate.exchange(this.baseUrl + leagueID, GET, Match[].class);
        ResponseEntity<Match[]> Matches = this.restTemplate.getForEntity(this.baseUrl + leagueID, Match[].class);

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStatusCode(Matches.getStatusCodeValue());
        matchDTO.setMatches(Matches.getBody());

        return matchDTO;
    }

}

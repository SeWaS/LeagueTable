package org.sewas.client;

import org.sewas.OpenLigaDBConfig;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.domain.model.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

/**
 * Created by sebastian on 21/05/17.
 */
@Component
public class OpenLigaDBClient {

    @Autowired
    private RestOperations restTemplate;

    /*
    @Value("${openliga.api.getmachdata}")
    private String baseUrl;
    */

    @Autowired
    private OpenLigaDBConfig openLigaDBConfig;

    public MatchDTO getMatchesForLeague(String leagueID){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        String url = this.openLigaDBConfig.getGetmachdata() + leagueID;

        ResponseEntity<Match[]> Matches = restTemplate.exchange(url, GET, entity, Match[].class);

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStatusCode(Matches.getStatusCodeValue());
        matchDTO.setMatches(Matches.getBody());

        return matchDTO;
    }

}

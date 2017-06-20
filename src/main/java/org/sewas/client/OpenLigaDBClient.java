package org.sewas.client;

import org.sewas.OpenLigaDBConfig;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.domain.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

/**
 * Created by sebastian on 21/05/17.
 */
@Component
public class OpenLigaDBClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenLigaDBConfig openLigaDBConfig;

    public MatchDTO getMatchesForLeagueMatchday(String leagueID, String season) throws SeasonNotAvailableException, OpenLigaDbNotOkException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        String url = this.openLigaDBConfig.getGetmachdata() + leagueID + "/" + season;

        ResponseEntity<Match[]> Matches;

        try{
            Matches = restTemplate.exchange(url, GET, entity, Match[].class);
        } catch (HttpClientErrorException e) {
            throw new OpenLigaDbNotOkException();
        }

        if(Matches.getBody().length == 0) {
            throw new SeasonNotAvailableException();
        }

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStatusCode(Matches.getStatusCodeValue());
        matchDTO.setMatches(Arrays.asList(Matches.getBody()));

        return matchDTO;
    }

    public MatchDTO getMatchesForLeagueMatchday(String leagueID, String season, String matchday) throws OpenLigaDbNotOkException, MatchdayNotAvailableException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        String url = this.openLigaDBConfig.getGetmachdata() + leagueID + "/" + season + "/" + matchday;

        ResponseEntity<Match[]> Matches;

        try{
            Matches = restTemplate.exchange(url, GET, entity, Match[].class);
        } catch (HttpClientErrorException e) {
            throw new OpenLigaDbNotOkException();
        }

        if(Matches.getBody().length == 0) {
            throw new MatchdayNotAvailableException();
        }

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStatusCode(Matches.getStatusCodeValue());
        matchDTO.setMatches(Arrays.asList(Matches.getBody()));

        return matchDTO;
    }
}

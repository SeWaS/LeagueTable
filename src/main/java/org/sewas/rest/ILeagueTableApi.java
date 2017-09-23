package org.sewas.rest;

import org.sewas.domain.model.LeagueTable;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sebastian on 21/05/17.
 */
public interface ILeagueTableApi {

    @GetMapping(path = "api/leagueTable/{leagueID}/{season}")
    String getCurrentTableForLeague(String leagueID, String season, Model model) throws SeasonNotAvailableException, OpenLigaDbNotOkException;

    @GetMapping(path = "api/leagueTable/{leagueID}/{season}/{matchday}")
    String getLeagueTableForMatchDay(String leagueID, String season, String matchday, Model model) throws MatchdayNotAvailableException, OpenLigaDbNotOkException;

}

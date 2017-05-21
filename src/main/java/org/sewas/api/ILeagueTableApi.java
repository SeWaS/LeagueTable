package org.sewas.api;

import org.sewas.domain.model.LeagueTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sebastian on 21/05/17.
 */
@RequestMapping("api/leagueTable/")
public interface ILeagueTableApi {

    @GetMapping(path = "{leagueID}/")
    ResponseEntity<LeagueTable> getCurrentTableForLeague(String leagueID);

}

package org.sewas.rest;

import org.sewas.api.ILeagueTableApi;
import org.sewas.domain.model.LeagueTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sebastian on 21/05/17.
 */
@RestController
public class LeagueTableController implements ILeagueTableApi {

    @Override
    public ResponseEntity<LeagueTable> getCurrentTableForLeague(String leagueID) {
        return null;
    }

}

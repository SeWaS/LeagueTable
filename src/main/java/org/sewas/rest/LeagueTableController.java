package org.sewas.rest;

import org.sewas.domain.model.LeagueTable;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.service.LeagueTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sebastian on 21/05/17.
 */
@RestController
public class LeagueTableController implements ILeagueTableApi {

    @Autowired
    private LeagueTableService leagueTableService;

    @Override
    public ResponseEntity<LeagueTable> getCurrentTableForLeague(@PathVariable(name = "leagueID")  String leagueID, @PathVariable(name = "season") String season) throws SeasonNotAvailableException, OpenLigaDbNotOkException {
        return new ResponseEntity<>(this.leagueTableService.returnCurrentLeagueTable(leagueID, season), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LeagueTable> getLeagueTableForMatchDay(@PathVariable(name = "leagueID") String leagueID, @PathVariable(name = "season") String season, @PathVariable(name = "matchday") String matchday) throws MatchdayNotAvailableException, OpenLigaDbNotOkException {
        return new ResponseEntity<>(this.leagueTableService.returnMatchdayLeagueTable(leagueID, season, matchday), HttpStatus.OK);
    }

}

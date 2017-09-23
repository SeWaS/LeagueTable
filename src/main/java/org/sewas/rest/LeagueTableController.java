package org.sewas.rest;

import org.sewas.domain.model.LeagueTable;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.service.LeagueTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sebastian on 21/05/17.
 */
@Controller
public class LeagueTableController implements ILeagueTableApi {

    private LeagueTableService leagueTableService;

    @Autowired
    public LeagueTableController(LeagueTableService leagueTableService) {
        this.leagueTableService = leagueTableService;
    }

    @Override
    public String getCurrentTableForLeague(@PathVariable("leagueID")  String leagueID,
                                           @PathVariable("season") String season,
                                           Model model) throws OpenLigaDbNotOkException, SeasonNotAvailableException {
        LeagueTable lt = this.leagueTableService.returnCurrentLeagueTable(leagueID, season);
        model.addAttribute("leagueID", leagueID);
        model.addAttribute("season", season);
        model.addAttribute("leagueTable", lt);
        return "currentMatchday";
    }

    @Override
    public String getLeagueTableForMatchDay(@PathVariable("leagueID") String leagueID,
                                            @PathVariable("season") String season,
                                            @PathVariable("matchday") String matchday,
                                            Model model) throws MatchdayNotAvailableException, OpenLigaDbNotOkException {
        LeagueTable lt = this.leagueTableService.returnMatchdayLeagueTable(leagueID, season, matchday);
        model.addAttribute("leagueID", leagueID);
        model.addAttribute("season", season);
        model.addAttribute("leagueTable", lt);
        return "currentMatchday";
    }

}

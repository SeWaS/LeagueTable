package org.sewas.features.steplibs.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.TeamPosition;
import org.sewas.features.util.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sebastian on 22/05/17.
 */
@Component
public class getCurrentTableStepDef
{

    @Autowired
    private World world;

    public void requestCurrentSeason()
    {
        this.world.setTableResponseEntity();
    }

    public void verifyThatAllTeamsHaveZeroPints()
    {
        LeagueTable responseLeagueTable = this.world.getResponse().getBody();

        assertThat(responseLeagueTable.getTable().size()).isEqualTo(4);

        for(TeamPosition t: responseLeagueTable.getTable())
        {
            assertThat(t.getPoints()).isEqualTo(0);
        }
    }

    public void addTeams(List<String> teams)
    {
        this.world.addTeams(teams);
    }

    public void createLeague(String leagueID)
    {
        this.world.setLeagueId(leagueID);
    }

    public void initWireMock() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String stubJson = "{}";

        stubFor(get(urlMatching("https://www.openligadb.de/api/getmatchdata/?"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(stubJson)));
    }
}

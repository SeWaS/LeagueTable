package org.sewas.features.steplibs.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.Match;
import org.sewas.domain.model.model.TeamPosition;
import org.sewas.features.util.World;
import org.sewas.util.MatchBuilder;
import org.sewas.util.MatchResultBuilder;
import org.sewas.util.TeamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by sebastian on 22/05/17.
 */
@Component
public class getCurrentTableStepDef
{

    @Autowired
    private World world;

    @Autowired
    private TestRestTemplate testRestTemplate;

    public void requestCurrentSeason()
    {
        this.world.setResponse(this.testRestTemplate.getForEntity("/api/leagueTable/"+this.world.getLeagueID(), LeagueTable.class));
    }

    public void verifyThatAllTeamsHaveZeroPints()
    {
        LeagueTable responseLeagueTable = this.world.getResponse().getBody();

        assertThat(responseLeagueTable.getTable().size()).isEqualTo(0);

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

        String stubJson = objectMapper.writeValueAsString(this.world.getMatches());

        stubFor(get(urlPathEqualTo("/api/getmatchdata/bl1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(stubJson)));
    }

    public void addMatch(String team1, String team2, int scoreTeam1, int scoreTeam2) {
        Match match = new MatchBuilder()
                .withMatchID(1)
                .withTeam1(new TeamBuilder()
                        .withTeamId(1)
                        .withTeamName(team1)
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamId(2)
                        .withTeamName(team2)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(1)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(2)
                        .withPointsForTeam1(scoreTeam1)
                        .withPointsForTeam2(scoreTeam2)
                        .build())
                .build();

        this.world.addMatch(match);
    }
}

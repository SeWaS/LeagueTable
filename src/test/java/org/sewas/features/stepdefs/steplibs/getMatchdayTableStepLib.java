package org.sewas.features.stepdefs.steplibs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sewas.domain.model.LeagueTable;
import org.sewas.domain.model.Match;
import org.sewas.features.util.World;
import org.sewas.util.MatchBuilder;
import org.sewas.util.MatchResultBuilder;
import org.sewas.util.TeamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sebastian on 18/06/17.
 */
@Component
public class getMatchdayTableStepLib {

    @Autowired
    private World world;

    @Autowired
    private TestRestTemplate testRestTemplate;

    public void setLeagueID(String leagueID) {
        this.world.setLeagueId(leagueID);
    }

    public void generateMatchesForLeague(int numOfMatchdays) {
        // TODO: generate Matches and add them to an array which will be added to worls.playedMatches

        // one loop representa a matchday - two matches per matchday
        for(int i=1;i<numOfMatchdays;i++) {

            Match match1 = new MatchBuilder()
                    .withTeam1(new TeamBuilder()
                        .withTeamId(1)
                        .withTeamName("Team1")
                        .build())
                    .withTeam2(new TeamBuilder()
                        .withTeamId(2)
                        .withTeamName("Team2")
                        .build())
                    .withResult(new MatchResultBuilder()
                        .withResultOrderID(1)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                    .withResult(new MatchResultBuilder()
                        .withResultOrderID(2)
                        .withPointsForTeam1(2)
                        .withPointsForTeam2(1)
                        .build())
                    .build();

            Match match2 = new MatchBuilder()
                    .withTeam1(new TeamBuilder()
                            .withTeamId(3)
                            .withTeamName("Team3")
                            .build())
                    .withTeam2(new TeamBuilder()
                            .withTeamId(4)
                            .withTeamName("Team4")
                            .build())
                    .withResult(new MatchResultBuilder()
                            .withResultOrderID(1)
                            .withPointsForTeam1(0)
                            .withPointsForTeam2(0)
                            .build())
                    .withResult(new MatchResultBuilder()
                            .withResultOrderID(2)
                            .withPointsForTeam1(0)
                            .withPointsForTeam2(1)
                            .build())
                    .build();

            Match[] matchday = {match1, match2};

            this.world.addMatchDay(matchday);
        }
    }

    public void requestMatchdayTable(int requestedMatchday) {
        String url = "/api/leagueTable/"+this.world.getLeagueID()+"/2016/"+requestedMatchday;
        this.world.setFetchForMatchdayResponse(this.testRestTemplate.getForEntity(url, LeagueTable.class));
    }

    public void initWiremock() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String stubJson = objectMapper.writeValueAsString(this.world.getPlayedMatchDays());

        stubFor(get(urlMatching("/api/getmatchdata/"+this.world.getLeagueID()+"/2016/(\\d+)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(stubJson)));
    }

    public void verifyThatCorrectLeagueWasReceived(String leagueID) {
        assertThat(this.world.getLeagueID()).isEqualTo(leagueID);
    }

    public void verifyThatCorrectMatchdayWasReceives(int requestedMatchday) {
        assertThat(this.world.getResponseForMatchdayTable().getStatusCodeValue()).isEqualTo(200);
        assertThat(this.world.getResponseForMatchdayTable().getBody().getTable().get(0).getNumberOfMatchDays()).isEqualTo(requestedMatchday);
    }

    public void verifyThatNoLeagueTableHasBeenReceived() {
        assertThat(this.world.getResponseForMatchdayTable().getStatusCodeValue()).isEqualTo(404);
        assertThat(this.world.getResponseForMatchdayTable().getBody()).isNull();
    }
}

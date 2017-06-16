package org.sewas.features.steplibs.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sewas.domain.model.LeagueTable;
import org.sewas.domain.model.Match;
import org.sewas.domain.model.TeamPosition;
import org.sewas.features.util.World;
import org.sewas.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        this.world.setResponse(this.testRestTemplate.getForEntity("/api/leagueTable/"+this.world.getLeagueID()+"/2016", LeagueTable.class));
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

    public void createLeague(String leagueID)
    {
        this.world.setLeagueId(leagueID);
    }

    public void initWireMock() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String stubJson = objectMapper.writeValueAsString(this.world.getMatches());

        stubFor(get(urlEqualTo("/api/getmatchdata/"+this.world.getLeagueID()+"/2016"))
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
                .withGroup(new GroupBuilder()
                        .withGroupOrderID(1)
                        .withGroupName("")
                        .withGroupOrderID(1)
                        .build())
                .withNumberOfViewers(10000)
                .build();

        this.world.addMatch(match);
    }

    public void verifyThatTeamHasPoints(String teamname, int place, int points) {
        LeagueTable receivedLeagueTable = this.world.getResponse().getBody();

        ListSearch listSearch = new ListSearch();

        TeamPosition t = listSearch.findTeamPositionByTeamname(teamname, receivedLeagueTable);

        assertThat(t.getPosition()).isEqualTo(place);
        assertThat(t.getPoints()).isEqualTo(points);
    }

    public void resetPlayedMatches() {
        this.world.resetMatches();
    }

    public void resetLeagueTable() {
        this.world.resetLeagueTable();
    }

    public void verifyNumberOfMatchdaysForTeam(String teamname, int numberOfMatchdays) {
        LeagueTable receivedLeagueTable = this.world.getResponse().getBody();

        ListSearch listSearch = new ListSearch();

        TeamPosition t = listSearch.findTeamPositionByTeamname(teamname, receivedLeagueTable);

        assertThat(t.getNumberOfMatchDays()).isEqualTo(numberOfMatchdays);
    }

    public void verifyVictoriesForTeamname(String teamname, int victories) {
        LeagueTable receivedLeagueTable = this.world.getResponse().getBody();

        ListSearch listSearch = new ListSearch();

        TeamPosition t = listSearch.findTeamPositionByTeamname(teamname, receivedLeagueTable);

        assertThat(t.getNumberOfVictories()).isEqualTo(victories);
    }

    public void verifyTiesForTeamname(String teamname, int ties) {
        LeagueTable receivedLeagueTable = this.world.getResponse().getBody();

        ListSearch listSearch = new ListSearch();

        TeamPosition t = listSearch.findTeamPositionByTeamname(teamname, receivedLeagueTable);

        assertThat(t.getNumberOfTies()).isEqualTo(ties);
    }

    public void verifyLossForTeamname(String teamname, int loss) {
        LeagueTable receivedLeagueTable = this.world.getResponse().getBody();

        ListSearch listSearch = new ListSearch();

        TeamPosition t = listSearch.findTeamPositionByTeamname(teamname, receivedLeagueTable);

        assertThat(t.getNumberOfLoss()).isEqualTo(loss);
    }
}

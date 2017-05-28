package org.sewas.features.steplibs;

import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.sewas.LeagueTableApplication;
import org.sewas.config.ScenarioConfig;
import org.sewas.domain.model.model.LeagueTable;
import org.sewas.features.steplibs.stepdefs.getCurrentTableStepDef;
import org.sewas.features.util.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

/**
 * Created by sebastian on 22/05/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {LeagueTableApplication.class, ScenarioConfig.class})
@AutoConfigureWireMock(port = 1234)
public class getCurrentTableStepLib {

    @Autowired
    private getCurrentTableStepDef steps;

    @Given("^League \"([^\"]*)\" exists$")
    public void leagueExists(String leagueId) {
        steps.createLeague(leagueId);
    }

    @And("^following teams are members: (.*)$")
    public void followingTeamsAreMembersABCD(List<String> teams) throws Throwable {
        steps.addTeams(teams);
    }

    @When("^Current league is fetched$")
    public void currentLeagueIsFetched() throws IOException {
        steps.initWireMock();
        steps.requestCurrentSeason();
    }

    @Then("^No team available at leaguetable$")
    public void allTeamsHavePoints() throws Throwable {
        steps.verifyThatAllTeamsHaveZeroPints();
    }

    @Given("^\"([^\"]*)\" played against \"([^\"]*)\" (\\d+):(\\d+)$")
    public void playedAgainst(String team1, String team2, int scoreTeam1, int scoreTeam2) {
        this.steps.addMatch(team1, team2, scoreTeam1, scoreTeam2);
    }

    @Then("^\"([^\"]*)\" is on place (\\d+) with (\\d+) points$")
    public void isOnPlaceWithPoints(String teamname, int place, int points) throws Throwable {
        this.steps.verifyThatTeamHasPoints(teamname, place, points);
    }

    @And("^\"([^\"]*)\" is on place (\\d+) with (\\d+) point$")
    public void isOnPlaceWithPoint(String teamname, int place, int points)  {
        this.steps.verifyThatTeamHasPoints(teamname, place, points);
    }
}

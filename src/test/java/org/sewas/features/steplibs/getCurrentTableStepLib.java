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
import org.sewas.features.steplibs.stepdefs.getCurrentTableStepDef;
import org.sewas.features.util.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by sebastian on 22/05/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {LeagueTableApplication.class, ScenarioConfig.class})
@AutoConfigureWireMock(port = 8999)
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
    public void currentLeagueIsFetched() throws JsonProcessingException {
        steps.initWireMock();
        steps.requestCurrentSeason();
    }

    @Then("^All teams have (\\d+) points$")
    public void allTeamsHavePoints(int arg0) throws Throwable {
        steps.verifyThatAllTeamsHaveZeroPints();
    }

    @Given("^\"([^\"]*)\" played against \"([^\"]*)\" (\\d+):(\\d+)$")
    public void playedAgainst(String arg0, String arg1, int arg2, int arg3) throws Throwable {

    }

    @Then("^\"([^\"]*)\" is on place (\\d+) with (\\d+) points$")
    public void isOnPlaceWithPoints(String arg0, int arg1, int arg2) throws Throwable {

    }
}

package org.sewas.features.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.sewas.LeagueTableApplication;
import org.sewas.config.ScenarioConfig;
import org.sewas.features.stepdefs.steplibs.getMatchdayTableStepLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

/**
 * Created by sebastian on 18/06/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {LeagueTableApplication.class, ScenarioConfig.class})
@AutoConfigureWireMock(port = 1234)
public class getMatchdayTableStepDef {

    @Autowired
    private getMatchdayTableStepLib steps;

    @Given("^League \"([^\"]*)\" has (\\d+) matchdays$")
    public void leagueHasMatchdays(String leagueID, int numOfMatchdays) {
        this.steps.setLeagueID(leagueID);
        this.steps.generateMatchesForLeague(numOfMatchdays);
    }

    @When("^League table for matchday (\\d+) of league \"([^\"]*)\" will be fetched$")
    public void leagueTableForMatchdayOfLeagueWillBeFetched(int requestedMatchday, String leagueID) throws JsonProcessingException {
        this.steps.initWiremock();
        this.steps.requestMatchdayTable(requestedMatchday);
    }

    @Then("^League table for matchday (\\d+) for league \"([^\"]*)\" will be received$")
    public void leagueTableForMatchdayForLeagueWillBeReceived(int requestedMatchday, String leagueID) {
        this.steps.verifyThatCorrectLeagueWasReceived(leagueID);
        this.steps.verifyThatCorrectMatchdayWasReceives(requestedMatchday);
    }

    @Then("^No league table will be fetched$")
    public void noLeagueTableWillBeFetched() {
        this.steps.verifyThatNoLeagueTableHasBeenReceived();
    }
}

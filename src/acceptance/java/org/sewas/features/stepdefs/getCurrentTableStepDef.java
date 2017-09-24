package org.sewas.features.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.sewas.LeagueTableApplication;
import org.sewas.features.ScenarioConfig;
import org.sewas.features.stepdefs.steplibs.getCurrentTableStepLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;

/**
 * Created by sebastian on 22/05/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {LeagueTableApplication.class, ScenarioConfig.class})
@AutoConfigureWireMock(port = 1234)
public class getCurrentTableStepDef {

    @Autowired
    private getCurrentTableStepLib steps;

    @Before
    public void setUp(){}

    @After
    public void tearDown(){
        this.steps.resetPlayedMatches();
    }

    @Given("^League \"([^\"]*)\" exists$")
    public void leagueExists(String leagueId) {
        steps.createLeague(leagueId);
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

    @And("^\"([^\"]*)\" has (\\d+) matchday played$")
    public void hasMatchdayPlayed(String teamname, int numberOfMatchdays)  {
        this.steps.verifyNumberOfMatchdaysForTeam(teamname, numberOfMatchdays);
    }

    @And("^\"([^\"]*)\" has (\\d+) matchdays played$")
    public void hasMatchdaysPlayed(String teamname, int numberOfMatchdays) {
        this.steps.verifyNumberOfMatchdaysForTeam(teamname, numberOfMatchdays);
    }

    @And("^\"([^\"]*)\" has (\\d+) victory, (\\d+) ties and (\\d+) loss$")
    public void hasVictoryTiesAndLoss(String teamname, int victories, int ties, int loss) {
        this.steps.verifyVictoriesForTeamname(teamname, victories);
        this.steps.verifyTiesForTeamname(teamname, ties);
        this.steps.verifyLossForTeamname(teamname, loss);
    }

    @And("^\"([^\"]*)\" has (\\d+) victories, (\\d+) tie and (\\d+) loss$")
    public void hasVictoriesTieAndLoss(String teamname, int victories, int ties, int loss) {
        this.steps.verifyVictoriesForTeamname(teamname, victories);
        this.steps.verifyTiesForTeamname(teamname, ties);
        this.steps.verifyLossForTeamname(teamname, loss);
    }

    @And("^\"([^\"]*)\" has (\\d+) victories, (\\d+) ties and (\\d+) loss$")
    public void hasVictoriesTiesAndLoss(String teamname, int victories, int ties, int loss) {
        this.steps.verifyVictoriesForTeamname(teamname, victories);
        this.steps.verifyTiesForTeamname(teamname, ties);
        this.steps.verifyLossForTeamname(teamname, loss);
    }

    @And("^\"([^\"]*)\" has goal difference of (\\d+):(\\d+)$")
    public void hasGoalDifferenceOf(String teamname, int goalsFor, int goalsAgainst) {
        this.steps.verifyGoalDifference(teamname, goalsFor, goalsAgainst);
    }
}

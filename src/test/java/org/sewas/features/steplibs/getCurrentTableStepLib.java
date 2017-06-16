package org.sewas.features.steplibs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.sewas.LeagueTableApplication;
import org.sewas.config.ScenarioConfig;
import org.sewas.features.steplibs.stepdefs.getCurrentTableStepDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sebastian on 22/05/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {LeagueTableApplication.class, ScenarioConfig.class})
@AutoConfigureWireMock(port = 1234)
public class getCurrentTableStepLib {

    @Autowired
    private getCurrentTableStepDef steps;

    @Before
    public void setUp(){}

    @After
    public void tearDown(){
        this.steps.resetPlayedMatches();
        this.steps.resetLeagueTable();
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
}

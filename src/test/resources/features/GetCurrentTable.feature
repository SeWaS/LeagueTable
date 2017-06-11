Feature: Get current league table

  Background:
    Given League "bl1" exists
    And following teams are members: Team1, Team2, Team3, Team4

  Scenario: Season has not started yet
    When Current league is fetched
    Then No team available at leaguetable

  Scenario: Season has one matchday
    Given "Team1" played against "Team2" 1:0
    And "Team3" played against "Team4" 2:2
    When Current league is fetched
    Then "Team1" is on place 1 with 3 points
    And "Team3" is on place 2 with 1 point
    And "Team4" is on place 2 with 1 point
    And "Team2" is on place 4 with 0 points

  @Pending
  Scenario: Season has two matchdays
    Given "Team1" played against "Team2" 1:0 on matchday 1
    And "Team3" played against "Team4" 2:2 on matchday 1
    And "Team1" played against "Team3" 4:0 on matchday 2
    And "Team2" played against "Team4" 1:1 on matchday 2
    When Current league is fetched
    Then "Team1" is on place 1 with 6 points
    And "Team4" is on place 2 with 2 points
    And "Team3" is on place 3 with 1 point
    And "Team2" is on place 3 with 1 point
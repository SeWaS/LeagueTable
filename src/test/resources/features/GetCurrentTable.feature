Feature: Get current league table

  Background:
    Given League "bl1" exists

  Scenario: Season has not started yet
    When Current league is fetched
    Then No team available at leaguetable

  Scenario: Season has one matchday
    Given "Team1" played against "Team2" 1:0
    And "Team3" played against "Team4" 2:2
    When Current league is fetched
    Then "Team1" is on place 1 with 3 points
    And "Team1" has 1 matchday played
    And "Team1" has 1 victory, 0 ties and 0 loss
    And "Team3" is on place 2 with 1 point
    And "Team3" has 1 matchday played
    And "Team3" has 0 victories, 1 tie and 0 loss
    And "Team4" is on place 2 with 1 point
    And "Team4" has 1 matchday played
    And "Team4" has 0 victories, 1 tie and 0 loss
    And "Team2" is on place 4 with 0 points
    And "Team2" has 1 matchday played
    And "Team2" has 0 victories, 0 ties and 1 loss

  Scenario: Season has two matchdays
    Given "Team1" played against "Team2" 1:0
    And "Team3" played against "Team4" 2:2
    And "Team1" played against "Team3" 4:0
    And "Team2" played against "Team4" 1:1
    When Current league is fetched
    Then "Team1" is on place 1 with 6 points
    And "Team1" has 2 matchdays played
    And "Team1" has 2 victories, 0 ties and 0 loss
    And "Team4" is on place 2 with 2 points
    And "Team4" has 2 matchdays played
    And "Team4" has 0 victories, 2 ties and 0 loss
    And "Team3" is on place 3 with 1 point
    And "Team3" has 2 matchdays played
    And "Team3" has 0 victories, 1 tie and 1 loss
    And "Team2" is on place 3 with 1 point
    And "Team2" has 2 matchdays played
    And "Team2" has 0 victories, 1 tie and 1 loss
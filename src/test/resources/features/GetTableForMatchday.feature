Feature: Get league table for specific machday

  Background:
    Given League "bl1" has 3 matchdays

  Scenario: Fetching league table for past matchday
    When League table for matchday 2 of league "bl1" will be fetched
    Then League table for matchday 2 for league "bl1" will be received

  Scenario: Fetching league table for current matchday
    When League table for matchday 3 of league "bl1" will be fetched
    Then League table for matchday 3 for league "bl1" will be received

  Scenario: Fetching league table for future matchday
    When League table for matchday 4 of league "bl1" will be fetched
    Then No league table will be fetched
    And Error message will be received
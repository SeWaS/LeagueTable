Feature: Get league table for specific machday

  Background:
    Given League "bl1" has 10 matchdays

  @Pending
  Scenario: Fetching league table for past matchday
    When League table for matchday 2 is fetched
    Then League table for matchday 2 will be received

  @Pending
  Scenario: Fetching league table for current matchday
    When League table for matchday 10 is fetched
    Then League table for matchday 10 will be received

  @Pending
  Scenario: Fetching league table for future matchday
    When League table for matchday 11 is fetched
    Then No league table will be fetched
    And Error message will be received
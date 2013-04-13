Feature: Load

  Scenario: Load Application Successfully
    Given the application loads
    When I navigate to home
    Then it works


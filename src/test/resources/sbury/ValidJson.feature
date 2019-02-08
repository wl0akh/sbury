Feature: Should Return Valid Json
  Json which contains product listing with total

  Scenario: process request
    Given valid url
    When request is maid
    Then I should get a vlaid Json
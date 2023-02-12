Feature: Testing API

  Scenario:Get status of given URL
    Given send the uri with given status
    Then verify that status code is "UP"
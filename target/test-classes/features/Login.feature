@login
Feature: Login to the website

  @smoke @regression
  Scenario: Successful login - happy path
    When user sends valid POST login request
    Then user is "successfully" logged in with status code 200
    And user gets the login message "Login Successfully"
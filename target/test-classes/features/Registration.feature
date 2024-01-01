@registration
Feature: Registration to the website

  @smoke @regression
  Scenario: Successful registration - happy path
    When user sends valid POST registration request
    Then user is "successfully" registered with status code 200
    And user gets the registration message "Registered Successfully"

  @regression
  Scenario: Unsuccessful registration - user tries to register with already used email address
    When user sends invalid POST registration request with existing email
    Then user is "unsuccessfully" registered with status code 400
    And user gets the registration message "User already exisits with this Email Id!"
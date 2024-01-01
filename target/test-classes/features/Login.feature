@login
Feature: Login to the website

  @smoke @regression
  Scenario Outline: Successful login - happy path
    When user sends POST login request with "<email>" and "<password>"
    Then user is "successfully" logged in with status code 200
    And user gets the login message "Login Successfully"
    Examples:
      | email                  | password       |
      | valid.email1@gmail.com | validPassword1 |
      | valid.email2@gmail.com | validPassword2 |

  @regression
  Scenario Outline: Unsuccessful login - invalid email, invalid password, invalid email & password
    When user sends POST login request with "<email>" and "<password>"
    Then user is "unsuccessfully" logged in with status code 400
    And user gets the login message "Incorrect email or password."
    Examples:
      | email                   | password        |
      | invalid.email@gmail.com | validPassword1  |
      | valid.email1@gmail.com  | invalidPassword |
      | invalid.email@gmail.com | invalidPassword |
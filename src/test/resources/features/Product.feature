@product
Feature: Get product information
  Background:
    Given user sends valid POST login request
    And user extracts authorization token

  @smoke @regression
  Scenario: Get all products
    When user sends valid POST all products request
    Then user gets list of all products with the message "All Products fetched Successfully"
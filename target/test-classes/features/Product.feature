@product
Feature: Product manipulation scenarios
  Background:
    Given user sends POST login request with "valid.email1@gmail.com" and "validPassword1"
    And user extracts authorization token and user Id

  @smoke @regression
  Scenario: Get all products
    When user sends valid POST all products request
    Then user gets list of all products with the message "All Products fetched Successfully"

  @smoke @regression
  Scenario: Get a specific product from the list based on the product Id
    When user sends valid POST all products request
    And  user gets list of all products with the message "All Products fetched Successfully"
    And user extracts information from one of the products
    And user sends GET request with the product Id to fetch desired product information
    Then user gets the product with the message "Product Details fetched Successfully"

  @smoke @regression
  Scenario: Add product to the cart
    When user sends valid POST all products request
    And  user gets list of all products with the message "All Products fetched Successfully"
    And user extracts information from one of the products
    And user sends POST add to cart request
    Then product is successfully added to the cart with the message "Product Added To Cart"
    And user sends GET cart request
    And user validates the cart content

  @smoke @regression
  Scenario: Creation of the order
    When user sends valid POST all products request
    And  user gets list of all products with the message "All Products fetched Successfully"
    And user extracts information from one of the products
    And user sends valid POST create order request
    Then user gets order Id with the message "Order Placed Successfully"
    And user sends valid GET order details request
    And user validate order details with the message "Orders fetched for customer Successfully"
package steps;

import api.pojos.Product.ProductList_Input;
import api.pojos.Product.ProductList_Output;
import api.requestSpecificationFactory.Registration_RequestSpecification;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductSteps {
    private Response response;

    @When("user sends valid POST all products request")
    public void userSendsValidPOSTAllProductsRequest() {
        String basePath = "/product/get-all-products";
        ProductList_Input product_List_input = new ProductList_Input();

        response = RestAssured.given().header("Authorization", LoginSteps.authorizationToken)
                .log().all().body(product_List_input)
                .spec(Registration_RequestSpecification.requestSpecification())
                .when().post(basePath).then().log().all().extract().response();
    }

    @Then("user gets list of all products with the message {string}")
    public void userGetsListOfAllProducts(String expectedMessage) {
        ProductList_Output productList_output = response.as(ProductList_Output.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedMessage, productList_output.getMessage());
        assertTrue(productList_output.getCount() > 0);
    }
}

package steps;

import api.pojos.Product.ProductList_Output;
import api.utils.RequestSpecificationFactory;
import api.utils.ApiClient;
import api.utils.Payloads;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductSteps {
    private Response response;

    @When("user sends valid POST all products request")
    public void userSendsValidPOSTAllProductsRequest() {
        String resource = "/product/get-all-products";

        Headers headers = new Headers(List.of(new Header("Authorization", LoginSteps.authorizationToken)));
        response = ApiClient.sendPostRequest(RequestSpecificationFactory.requestSpecificationWithHeaders(headers),
                Payloads.emptyProductPayload(), resource);
    }

    @Then("user gets list of all products with the message {string}")
    public void userGetsListOfAllProducts(String expectedMessage) {
        ProductList_Output productList_output = response.as(ProductList_Output.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedMessage, productList_output.getMessage());
        assertTrue(productList_output.getCount() > 0);
    }
}

package steps;

import api.pojos.Product.AddToCart_Input;
import api.pojos.Product.Product;
import api.pojos.Product.ProductList_Output;
import api.pojos.Product.Product_Output;
import api.utils.RequestSpecificationFactory;
import api.utils.ApiClient;
import api.utils.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductSteps {

    private Headers headers;
    private Response response;
    private Product productFromList;

    @When("user sends valid POST all products request")
    public void userSendsValidPOSTAllProductsRequest() {
        String resource = "/product/get-all-products";

        headers = new Headers(List.of(new Header("Authorization", LoginSteps.authorizationToken)));
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

    @And("user extracts information from one of the products")
    public void userExtractsInformationFromOneOfTheProducts() {
        ProductList_Output productList_output = response.as(ProductList_Output.class);
        Random random = new Random();
        int randomIndex = random.nextInt(productList_output.getData().size());
        productFromList = productList_output.getData().get(randomIndex);
    }

    @And("user sends GET request with the product Id to fetch desired product information")
    public void userSendsGETRequestWithTheProductIdToFetchDesiredProductInformation() {
        String resource = "/product/get-product-detail/" + productFromList.get_id();
        response = ApiClient.sendGetRequest(RequestSpecificationFactory.requestSpecificationWithHeaders(headers), resource);
    }

    @Then("user gets the product with the message {string}")
    public void userGetsTheProductWithTheMessage(String expectedMessage) {
        Product_Output product_output = response.as(Product_Output.class);
        Product product = product_output.getData();
        assertEquals(productFromList, product);
        assertEquals(expectedMessage, product_output.getMessage());
        assertTrue(response.statusCode() < 400);
    }

    @And("user sends POST add to cart request")
    public void userSendsPOSTAddToCartRequest() {
        String resource = "/user/add-to-cart";
        AddToCart_Input addToCart_input = AddToCart_Input.builder()._id(LoginSteps.userId).product(productFromList).build();
        response = ApiClient.sendPostRequest
                (RequestSpecificationFactory.requestSpecificationWithHeaders(headers), addToCart_input, resource);
    }

    @Then("product is successfully added to the cart with the message {string}")
    public void productIsSuccessfullyAddedToTheCartWithTheMessage(String expectedMessage) {
        JsonPath jsonPath = new JsonPath(response.asString());
        assertEquals(200, response.statusCode());
        assertEquals(expectedMessage,jsonPath.getString("message"));
    }
}

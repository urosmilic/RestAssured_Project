package steps;

import api.pojos.cart.AddToCart_Input;
import api.pojos.cart.Cart_Output;
import api.pojos.order.*;
import api.pojos.product.*;
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
    private String orderId;
    private Order order;

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

    @And("user sends GET cart request")
    public void userSendsGETCartRequest() {
        String resource = "/user/get-cart-products/" + LoginSteps.userId;
        response = ApiClient.sendGetRequest(RequestSpecificationFactory.requestSpecificationWithHeaders(headers),resource);
    }

    @And("user validates the cart content")
    public void userValidatesTheCartContent() {
        assertEquals(200, response.statusCode());
        Cart_Output cart_output = response.as(Cart_Output.class);
        assertEquals(1, cart_output.getCount());
        assertEquals("Cart Data Found", cart_output.getMessage());
        assertEquals(1, cart_output.getProducts().size());
        assertEquals(productFromList, cart_output.getProducts().get(0));
    }

    @When("user sends valid POST create order request")
    public void userSendsValidPOSTCreateOrderRequest() {
        String resource = "/order/create-order";
        order = Order.builder().productOrderedId(productFromList.get_id()).country("Serbia").build();
        List<Order> orders = List.of(order);
        Order_input order_input = Order_input.builder().orders(orders).build();
        response = ApiClient.sendPostRequest(RequestSpecificationFactory.requestSpecificationWithHeaders(headers), order_input, resource);
    }

    @Then("user gets order Id with the message {string}")
    public void userGetsOrderIdWithTheMessage(String expectedMessage) {
        CreateOrder_Output createOrder_output = response.as(CreateOrder_Output.class);
        orderId = createOrder_output.getOrders().get(0);
        assertEquals(201, response.statusCode());
        assertEquals(productFromList.get_id(), createOrder_output.getProductOrderId().get(0));
        assertEquals(expectedMessage, createOrder_output.getMessage());
    }

    @And("user sends valid GET order details request")
    public void userSendsValidGETOrderDetailsRequest() {
        String resource = "/order/get-orders-details?id=" + orderId;
        response = ApiClient.sendGetRequest(RequestSpecificationFactory.requestSpecificationWithHeaders(headers),resource);
    }

    @And("user validate order details with the message {string}")
    public void userValidateOrderDetailsWithTheMessage(String expectedMessage) {
        OrderDetails_Output orderDetails_output = response.as(OrderDetails_Output.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedMessage, orderDetails_output.getMessage());
        OrderDetails orderDetails = orderDetails_output.getData();
        assertEquals(orderId, orderDetails.get_id());
        assertEquals(productFromList.getProductName(), orderDetails.getProductName());
        assertEquals(productFromList.get_id(), orderDetails.getProductOrderedId());
        assertEquals(order.getCountry(), orderDetails.getCountry());
        assertEquals(LoginSteps.userId, orderDetails.getOrderById());
        assertEquals(LoginSteps.userEmail, orderDetails.getOrderBy());
    }
}

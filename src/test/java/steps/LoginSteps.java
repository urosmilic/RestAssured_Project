package steps;

import api.pojos.Login.Login_Output;
import api.utils.RequestSpecificationFactory;
import api.utils.ApiClient;
import api.utils.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class LoginSteps {
    private Response response;
    protected static String authorizationToken;
    protected static String userId;

    @When("user sends POST login request with {string} and {string}")
    public void userSendsPOSTLoginRequest(String email, String password) {
        String resource = "/auth/login";
        response = ApiClient.sendPostRequest
                (RequestSpecificationFactory.requestSpecification(), Payloads.loginPayload(email, password), resource);
    }

    @Then("user is {string} logged in with status code {int}")
    public void userIsLoggedInWithStatusCode(String success, int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @And("user gets the login message {string}")
    public void userGetsTheLoginMessage(String expectedMessage) {
        JsonPath jsonPath = new JsonPath(response.asString());
        String message = jsonPath.getString("message");
        assertEquals(expectedMessage, message);
    }

    @And("user extracts authorization token and user Id")
    public void userExtractsAuthorizationTokenAndUserId() {
        Login_Output login_output = response.as(Login_Output.class);
        authorizationToken = login_output.getToken();
        userId = login_output.getUserId();
    }
}
package steps;

import api.pojos.Login.Login_Output;
import api.requestSpecificationFactory.Registration_RequestSpecification;
import api.utils.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class LoginSteps {
    private Response response;
    public static String authorizationToken;
    @When("user sends valid POST login request")
    public void userSendsValidPOSTLoginRequest() {
        String basePath = "/auth/login";

        response = RestAssured.given()
                .log().all()
                .spec(Registration_RequestSpecification.requestSpecification())
                .body(Payloads.loginPayload())
                .when().post(basePath).then().log().all().extract().response();
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

    @And("user extracts authorization token")
    public void userExtractsAuthorizationToken() {
        Login_Output login_output = response.as(Login_Output.class);
        authorizationToken = login_output.getToken();
    }
}
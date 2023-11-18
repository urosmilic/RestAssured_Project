package steps;

import api.pojos.Registration;
import api.requestSpecificationFactory.Registration_RequestSpecification;
import api.utils.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class RegistrationSteps {

    private static Response response;
    private Registration registration;
    private static String usedEmailAddress;

    @When("user sends valid POST registration request")
    public void userSendsValidDataAndRegister() {
        String basePath = "/auth/register";

        registration = Payloads.registrationPayload();
        usedEmailAddress = registration.getUserEmail();

        response = RestAssured.given()
                .log().all()
                .spec(Registration_RequestSpecification.requestSpecification())
                .body(registration)
                .when().post(basePath).then().log().all().extract().response();
    }

    @Then("user is {string} registered with status code {int}")
    public void userGetsStatusCodeAfterRegistrationRequest(String success, int statusCode) {
        assertEquals(response.statusCode(),statusCode);
    }

    @And("user gets the registration message {string}")
    public void userGetsTheMessageAfterRegistrationRequest(String expectedMessage) {
        JsonPath js = new JsonPath(response.asString());
        String message = js.getString("message");
        assertEquals(message, expectedMessage);
    }

    @When("user sends invalid POST registration request with existing email")
    public void userSendsInvalidPOSTRegistrationRequestWithExistingEmail() {
        String basePath = "/auth/register";

        registration = Payloads.registrationPayload();
        registration.setUserEmail(usedEmailAddress);
        response = RestAssured.given()
                .log().all()
                .spec(Registration_RequestSpecification.requestSpecification())
                .body(registration)
                .when().post(basePath).then().log().all().extract().response();
    }
}
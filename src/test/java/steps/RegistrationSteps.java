package steps;

import api.pojos.Registration.Registration_Input;
import api.utils.RequestSpecificationFactory;
import api.utils.ApiClient;
import api.utils.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class RegistrationSteps {

    private static Response response;
    private Registration_Input registrationInput;
    private static String usedEmailAddress;

    @When("user sends valid POST registration request")
    public void userSendsValidDataAndRegister() {
        String resource = "/auth/register";

        registrationInput = Payloads.registrationPayload();
        usedEmailAddress = registrationInput.getUserEmail();

        response = ApiClient.sendPostRequest
                (RequestSpecificationFactory.requestSpecification(), registrationInput, resource);
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
        String resource = "/auth/register";

        registrationInput = Payloads.registrationPayload();
        registrationInput.setUserEmail(usedEmailAddress);

        response = ApiClient.sendPostRequest
                (RequestSpecificationFactory.requestSpecification(), registrationInput, resource);
    }
}
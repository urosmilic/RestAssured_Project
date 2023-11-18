package api.utils;

import api.pojos.Login.Login_Input;
import api.pojos.Registration.Registration_Input;
import com.github.javafaker.Faker;

public class Payloads {

    public static Registration_Input registrationPayload() {
        Faker faker = new Faker();

        return Registration_Input.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .userEmail(faker.internet().safeEmailAddress())
                .userRole("customer")
                .occupation("Engineer")
                .gender("Male")
                .userMobile("1234567891")
                .userPassword("Test123!")
                .confirmPassword("Test123!")
                .required(true)
                .build();
    }

    public static Login_Input loginPayload() {
        return Login_Input.builder()
                .userEmail("testiranje12345@gmail.com")
                .userPassword("Testiranje1234")
                .build();
    }
}
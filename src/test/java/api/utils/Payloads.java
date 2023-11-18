package api.utils;

import api.pojos.Login;
import api.pojos.Registration;
import com.github.javafaker.Faker;

public class Payloads {

    public static Registration registrationPayload() {
        Faker faker = new Faker();

        return Registration.builder()
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

    public static Login loginPayload() {
        return Login.builder()
                .userEmail("testiranje12345@gmail.com")
                .userPassword("Testiranje1234")
                .build();
    }
}
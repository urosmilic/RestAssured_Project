package api.utils;

import api.pojos.Login.Login_Input;
import api.pojos.Product.Product;
import api.pojos.Product.ProductList_Input;
import api.pojos.Registration.Registration_Input;
import com.github.javafaker.Faker;

import java.util.ArrayList;

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

    public static Login_Input loginPayload(String mail, String password) {
        return Login_Input.builder()
                .userEmail(mail)
                .userPassword(password)
                .build();
    }

    public static ProductList_Input emptyProductPayload() {
        return ProductList_Input.builder()
                .maxPrice(null).minPrice(null)
                .productCategory(new ArrayList<>())
                .productFor(new ArrayList<>())
                .productName("")
                .productSubCategory(new ArrayList<>())
                .build();
    }
}
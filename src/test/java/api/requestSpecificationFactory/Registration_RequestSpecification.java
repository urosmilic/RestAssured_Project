package api.requestSpecificationFactory;

import api.utils.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Registration_RequestSpecification {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder().build()
                .baseUri(Constants.baseURI).contentType(ContentType.JSON);
    }

}
package api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationFactory {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder().build()
                .baseUri(Constants.baseURI).contentType(ContentType.JSON);
    }

    public static RequestSpecification requestSpecificationWithHeaders(Headers headers) {
        return new RequestSpecBuilder().build()
                .baseUri(Constants.baseURI).contentType(ContentType.JSON).headers(headers);
    }

}
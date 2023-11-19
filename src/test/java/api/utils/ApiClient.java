package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public static Response sendGetRequest(RequestSpecification reqSpec, String  resourcePath) {
        return RestAssured.given().baseUri(Constants.baseURI)
                .log().all()
                .spec(reqSpec)
                .when()
                .get(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response sendPostRequest(RequestSpecification reqSpec, Object payload, String  resourcePath) {
        return RestAssured.given().baseUri(Constants.baseURI)
                .log().all()
                .spec(reqSpec)
                .body(payload)
                .when()
                .post(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }


}

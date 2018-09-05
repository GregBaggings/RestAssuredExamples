package io.git.core;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomRestAssuredMethods {

    public CustomRestAssuredMethods() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    private RequestSpecification given() {
        return RestAssured.given().relaxedHTTPSValidation().basePath("");
    }

    protected Response GET(String url, String contentType, int expectedStatusCode) {
        return given().relaxedHTTPSValidation().contentType(contentType).and().expect().statusCode(expectedStatusCode).when().get(url);
    }

    private Response POST(String url, String contentType, String body, int expectedStatusCode) {
        return given().relaxedHTTPSValidation().contentType(contentType).body(body).and().expect().statusCode(expectedStatusCode).when().post(url);
    }

    private Response PUT(String url, String contentType, int expectedStatusCode) {
        return given().relaxedHTTPSValidation().contentType(contentType).and().expect().statusCode(expectedStatusCode).when().put(url);
    }
}

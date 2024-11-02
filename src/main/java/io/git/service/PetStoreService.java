package io.git.service;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetStoreService {
    // https://petstore.swagger.io/
    private final String BASE_URI = "https://petstore.swagger.io/v2";

    private final RequestSpecification requestSpec = RestAssured.given()
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
            .relaxedHTTPSValidation()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .baseUri(BASE_URI);

    public Response postPet(Object body) {
        return requestSpec
                .body(body)
                .when()
                .post("/pet")
                .then().extract()
                .response();
    }

}

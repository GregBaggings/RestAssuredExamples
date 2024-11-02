package io.git.service;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StarWarsService {
    // https://swapi.dev/
    private final String BASE_URI = "https://swapi.dev/api";

    private final RequestSpecification requestSpec = RestAssured.given()
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
            .relaxedHTTPSValidation()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .baseUri(BASE_URI);

    public Response getCharacterDetailsWihId(int id) {
        return requestSpec
                .pathParam("id", id)
                .when()
                .get("/people/{id}")
                .then().extract()
                .response();
    }

    public Response getPlanetDetailsWithId(int id) {
        return requestSpec
                .pathParam("id", id)
                .when()
                .get("/planets/{id}")
                .then().extract()
                .response();
    }

    public Response getSpaceShipDetailsWithId(int id) {
        return requestSpec
                .pathParam("id", id)
                .when()
                .get("/starships/{id}")
                .then().extract()
                .response();
    }

}

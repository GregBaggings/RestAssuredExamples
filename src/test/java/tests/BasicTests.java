package tests;

import io.git.core.CustomRestAssuredMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class BasicTests extends CustomRestAssuredMethods {

    private static final String INVENTORY_ENDPOINT = "http://petstore.swagger.io/v2/store/inventory";
    private static final String APPLICATION_JSON = "application/json";

    @Test
    public void basicInventoryJsonTest() {
        Response response = RestAssured.given().log().all().expect().statusCode(200).and().contentType(APPLICATION_JSON).when().get(INVENTORY_ENDPOINT);
        response.prettyPrint();
    }

    @Test
    public void wrappedVersionJsonHappyPath() {
        // See method source for details
        GET(INVENTORY_ENDPOINT, APPLICATION_JSON, 200);
    }
}

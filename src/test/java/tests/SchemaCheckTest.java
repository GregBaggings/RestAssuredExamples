package tests;

import io.git.storage.SchemaStorage;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaCheckTest {

    private static final String GAME_OF_THRONES_API = "https://anapioficeandfire.com/api/characters/{id}";

    @Test
    public void schemaTest() {
        Response response = given().log().all().pathParam("id", 583).expect().statusCode(200).when().get(GAME_OF_THRONES_API);

        // Hardcoded version - for Demo purpose
        // Assert.assertThat(response.asString(), matchesJsonSchemaInClasspath("GameOfThronesSchema.json"));

        Assert.assertThat(response.asString(), matchesJsonSchemaInClasspath(SchemaStorage.GAME_OF_THRONES.getSchemaPath()));
        System.out.println("Response was matching to the given JSON schema. Actual response was: \n" + response.asString() + "\n");
    }
}

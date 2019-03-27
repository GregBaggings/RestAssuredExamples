package tests;

import io.git.models.User;
import io.git.storage.BodyStorage;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class FullFlowExampleTest {

    private static final String CREATE_USER_ENDPOINT = "http://petstore.swagger.io/v2/user";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String APPLICATION_JSON = "application/json";

    @Test
    public void multipleRequestsToCompleteAFullFlowTest() throws IOException {
        // Hardcoded demo how to read a full json file to a string

        // Step0. - Set the test data
        String userName = "JL";
        String body = FileUtils.readFileToString(new File(BodyStorage.CREATE_USER.getBodyPath()), "UTF-8");

        // Step1. - Create the user in the DB
        Response createUser = given().log().all().body(body).and().contentType("application/json").expect().statusCode(200).when().post(CREATE_USER_ENDPOINT);
        System.out.println(createUser.asString());
        System.out.println("User was created with user name: " + userName + "\n");

        // Step2. - Get back the user details
        Response getUser = given().log().all().header(ACCEPT_HEADER, APPLICATION_JSON).and().pathParam("username", userName).when()
                .get("http://petstore.swagger.io/v2/user/{username}");
        System.out.println("The user details are: " + getUser.asString() + "\n");

        // Step2. - Get back the user details more professional
        User userDetails = given().log().all().header(ACCEPT_HEADER, APPLICATION_JSON).and().pathParam("username", userName).when()
                .get("http://petstore.swagger.io/v2/user/{username}").as(User.class);
        System.out.println("The user details are: " + userDetails.toString() + "\n");
        assertEquals("Username was not correct.", "JL", userDetails.getUsername());

        // Step3. - Delete the user
        given().log().all().pathParam("username", userName).when().delete("http://petstore.swagger.io/v2/user/{username}");
        System.out.println("User delete: Done.");

        // Step4. - Check if the user is really deleted from the DB
        Response getUserAfterDelete = given().log().all().header("Accept", "application/json").and().pathParam("username", userName).and().expect()
                .statusCode(404).when().get("http://petstore.swagger.io/v2/user/{username}");
        System.out.println("The user details are: " + getUserAfterDelete.asString());
        System.out.println("User is really deleted.");
    }

}

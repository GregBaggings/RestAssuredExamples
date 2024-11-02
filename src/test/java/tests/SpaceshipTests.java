package tests;

import io.git.service.StarWarsService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpaceshipTests {

    private final StarWarsService service = new StarWarsService();

    @Test
    public void tryToGetSpaceshipDetails() {
        Response spaceshipDetails = service.getSpaceShipDetailsWithId(3);
        Assert.assertEquals(spaceshipDetails.getStatusCode(),200);
    }

    @Test
    public void tryToGetSpaceshipDetailsErrorCase() {
        Response spaceshipDetails = service.getSpaceShipDetailsWithId(1);
        Assert.assertEquals(spaceshipDetails.getStatusCode(),404);
     }

}

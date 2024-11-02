package tests;

import io.git.objects.starwars.Character;
import io.git.service.StarWarsService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CharacterTests {

    private final StarWarsService service = new StarWarsService();

    @Test
    public void tryToGetCharacterDetails() {
        Response characterDetails = service.getCharacterDetailsWihId(1);
        Assert.assertEquals(characterDetails.getStatusCode(), 200);

        String name = characterDetails.jsonPath().getString("name");
        Assert.assertEquals(name, "Luke Skywalker");

        Character character = characterDetails.as(Character.class);
        Assert.assertEquals(character.getName(), "Luke Skywalker");
        Assert.assertEquals(character.getEyeColor(), "blue");
        Assert.assertEquals(character.getHairColor(), "blond");
    }

}

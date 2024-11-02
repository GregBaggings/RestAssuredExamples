package tests;

import com.google.gson.Gson;
import io.git.objects.petstore.Pet;
import io.git.service.PetStoreService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddPetTest {

    private final PetStoreService service = new PetStoreService();

    private final String json = "{\n" +
            "  \"id\": 1000,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  },\n" +
            "  \"name\": \"doggie\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    @Test
    public void tryToCreateNewPet() {
        Response postPet = service.postPet(json);
        Assert.assertEquals(postPet.getStatusCode(), 200);
    }

    @Test
    public void tryToCreateNewPetWithMappedBody() {
        Pet pet = new Gson().fromJson(json, Pet.class);
        Response postPet = service.postPet(pet);
        Assert.assertEquals(postPet.getStatusCode(), 200);
    }

}

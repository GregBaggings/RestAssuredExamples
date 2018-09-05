package io.git.storage;

public enum BodyStorage {

    //Add other bodies here...
    CREATE_USER("src/test/resources/bodies/CreateUserBody.json");

    public String body;

    BodyStorage(String body) {
        this.body = body;
    }

    public String getBodyPath() {
        return body;
    }
}

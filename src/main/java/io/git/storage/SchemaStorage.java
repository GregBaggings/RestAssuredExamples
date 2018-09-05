package io.git.storage;

public enum SchemaStorage {

    //Add other schemas here...
    GAME_OF_THRONES("./schemas/GameOfThronesSchema.json");

    public String schema;

    SchemaStorage(String schema) {
        this.schema = schema;
    }

    public String getSchemaPath() {
        return schema;
    }
}

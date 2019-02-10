package subry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;

public class SearchService {
    public JsonObject search() {
        JsonObject total = Json.createObjectBuilder()
        .add("gross", 1.12)
        .add("vat", 1.12)
        .build();
        JsonArray results = Json.createArrayBuilder()
        .add(
            Json.createObjectBuilder()
            .add("title","value")
            .add("kcal_per_100g","value")
            .add("unit_price",1.12)
            .add("description","value")
            .build()
            )
        .build();
        JsonObject searchResult = Json.createObjectBuilder()
        .add("results", results)
        .add("total", total)
        .build();
        return searchResult;

    }
}

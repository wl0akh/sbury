package sbury;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import subry.SearchService;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonNumber;
import javax.json.JsonValue.ValueType;

public class TestSearchService {
    @Test
    public void testSearch() {
        SearchService searchService = new SearchService();
        Class expectedJsonType = Json.createObjectBuilder().build().getClass();
        Class expectedJsonArrayType = Json.createArrayBuilder().build().getClass();
        JsonObject searcResult = searchService.search();
        assertThat(searcResult, instanceOf(expectedJsonType));
        assertThat(searcResult.getValue("/results").getValueType(), is(ValueType.ARRAY));
        assertThat(searcResult.getValue("/total").getValueType(), is(ValueType.OBJECT));
        assertThat(searcResult.getValue("/total/gross").getValueType(),is(ValueType.STRING));
        assertThat(searcResult.getValue("/total/vat").getValueType(),is(ValueType.STRING));
    }

}
package sbury;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sbury.Filter;
import sbury.Sort;
import sbury.JsonViewProcessor;
import sbury.FetchService;

import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestFetchService {
    private Filter allowAll;
    private Sort sortBy;
    private DataSource htmlDataSource;
    private JsonViewProcessor jsonViewProcessor;
    private FetchService fetchService;

    @Before
    public void initiliseDataSourceVariables() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
        allowAll = (e) -> (true);
        sortBy = (p1,p2)->(1);
        DataSource htmlDataSource = new HtmlDataSource(url, EntityConfig.getEntities());
        jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll, sortBy);
        fetchService = new FetchService(jsonViewProcessor);
    }

    @Test
    public void testFetchService() {
        String json = fetchService.fetch();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        JsonObject jsonTreeObject = jsonTree.getAsJsonObject();

        assertTrue(jsonTree.isJsonObject());
        assertTrue(jsonTreeObject.get("results").isJsonArray());

        JsonArray results = jsonTreeObject.get("results").getAsJsonArray();
        assertTrue(results.get(0).getAsJsonObject().get("title").getAsString().contains("Strawberries 400g"));
        assertTrue(results.get(0).getAsJsonObject().get("kcal_per_100g").getAsString().contains("33"));
        assertTrue(results.get(0).getAsJsonObject().get("description").getAsString().contains("strawberries"));

        JsonElement total = jsonTreeObject.get("total");
        JsonElement gross = total.getAsJsonObject().get("gross");
        JsonElement vat = total.getAsJsonObject().get("vat");
        assertEquals((0.2 * (gross.getAsDouble())), vat.getAsDouble(), 0.001);

        Double expectedGross = 0.0;
        for (JsonElement element : results) {
            expectedGross += element.getAsJsonObject().get("unit_price").getAsDouble();
        }
        assertEquals(expectedGross, gross.getAsDouble(), 0.001);

    }

}
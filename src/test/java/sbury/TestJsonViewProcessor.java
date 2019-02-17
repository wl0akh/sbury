package sbury;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

import sbury.Filter;
import sbury.Sort;
import sbury.HtmlDataSource;
import sbury.JsonViewProcessor;
import sbury.DataSource;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class TestJsonViewProcessor {
    private Filter allowAll;
    private Sort sortBy;
    private DataSource htmlDataSource;
    private ViewProcessor jsonViewProcessor;

    @Before
    public void initiliseDataSourceVariables() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";

        htmlDataSource = new HtmlDataSource(url, EntityConfig.getEntities());
        allowAll = (e) -> (true);
        sortBy = (p1, p2) -> (1);
        jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll, sortBy);
    }

    @Test
    public void testProcessValidUrl() {
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        assertTrue(jsonTree.isJsonObject());
    }

    @Test
    public void testProcessWithSortByUnitPriceAssending() {
        sortBy = (p1, p2) -> {
            Map p1Map = (Map) p1;
            Map p2Map = (Map) p2;

            Double e1 = (Double) p1Map.get("unit_price");
            Double e2 = (Double) p2Map.get("unit_price");

            if (e1 > e2)
                return 1;
            else if (e1 < e2)
                return -1;
            else
                return 0;
        };
        jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll, sortBy);
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        JsonObject jsonTreeObject = jsonTree.getAsJsonObject();
        JsonArray results = jsonTreeObject.get("results").getAsJsonArray();
        assertTrue(results.get(0).getAsJsonObject().get("unit_price").getAsDouble() <= results.get(1).getAsJsonObject()
                .get("unit_price").getAsDouble());
    }

    @Test
    public void testProcessInvalidUrl() {
        String url = "***Invalid_URL**";
        DataSource htmlDataSource = new HtmlDataSource(url, EntityConfig.getEntities());
        allowAll = (e) -> (true);
        sortBy = (p1, p2) -> (1);
        jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll, sortBy);
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        JsonObject jsonTreeObject = jsonTree.getAsJsonObject();

        assertTrue(jsonTree.isJsonObject());
        assertEquals(jsonTreeObject.get("results").getAsJsonArray().size(), 0);
    }

}
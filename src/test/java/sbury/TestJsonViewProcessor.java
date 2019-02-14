package sbury;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sbury.Filter;
import sbury.Products;
import sbury.JsonViewProcessor;
import sbury.Product;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestJsonViewProcessor {
    private Filter allowAll;
    private Products products;
    private JsonViewProcessor jsonViewProcessor;

    @Before
    public void initiliseDataSourceVariables() {
        EntityConfiguration.url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
   
        products = new SburyProducts();
        allowAll = (e) -> (true);
        jsonViewProcessor = new JsonViewProcessor(products, allowAll);
    }

    @Test
    public void testProcessValidUrl() {
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        assertTrue(jsonTree.isJsonObject());
    }

    @Test
    public void testProcessInvalidUrl() {
        EntityConfiguration.url = "***Invalid_URL**";
        products = new SburyProducts();
        allowAll = (e) -> (true);
        jsonViewProcessor = new JsonViewProcessor(products, allowAll);
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        JsonObject jsonTreeObject = jsonTree.getAsJsonObject();

        assertTrue(jsonTree.isJsonObject());
        // assertEquals(jsonTreeObject.get("ERROR").getAsString(), "Unable to Fetch Data From "+url);
    }

}
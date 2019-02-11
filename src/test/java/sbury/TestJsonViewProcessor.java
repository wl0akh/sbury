package sbury;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import subry.Filter;
import subry.HtmlDataSource;
import subry.JsonViewProcessor;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestJsonViewProcessor {
    private Filter allowAll;
    private HtmlDataSource htmlDataSource;
    private JsonViewProcessor jsonViewProcessor;

    @Before
    public void initiliseDataSourceVariables() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
        htmlDataSource = new HtmlDataSource(url);
        allowAll = (e) -> (true);
        jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll);
    }

    @Test
    public void testProcess() {
        String json = jsonViewProcessor.process();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(json);
        assertTrue(jsonTree.isJsonObject());
    }

}
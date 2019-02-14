// package sbury;

// import org.junit.Test;
// import org.junit.Before;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// import sbury.Filter;
// import sbury.HtmlDataSource;
// import sbury.JsonViewProcessor;
// import sbury.Product;

// import com.google.gson.JsonParser;
// import com.google.gson.JsonElement;
// import com.google.gson.JsonObject;

// public class TestJsonViewProcessor {
//     private Filter allowAll;
//     private Map<String, Product> entityHash;
//     private HtmlDataSource htmlDataSource;
//     private JsonViewProcessor jsonViewProcessor;
//     private String url;

//     @Before
//     public void initiliseDataSourceVariables() {
//         url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
//         entityHash = EntityConfiguration.getEntityHash();
//         htmlDataSource = new HtmlDataSource(url, entityHash);
//         allowAll = (e) -> (true);
//         jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll);
//     }

//     @Test
//     public void testProcessValidUrl() {
//         String json = jsonViewProcessor.process();
//         JsonParser jsonParser = new JsonParser();
//         JsonElement jsonTree = jsonParser.parse(json);
//         assertTrue(jsonTree.isJsonObject());
//     }

//     @Test
//     public void testProcessInvalidUrl() {
//         url = "***Invalid_URL**";
//         htmlDataSource = new HtmlDataSource(url, entityHash);
//         jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll);
//         String json = jsonViewProcessor.process();
//         JsonParser jsonParser = new JsonParser();
//         JsonElement jsonTree = jsonParser.parse(json);
//         JsonObject jsonTreeObject = jsonTree.getAsJsonObject();

//         assertTrue(jsonTree.isJsonObject());
//         assertEquals(jsonTreeObject.get("ERROR").getAsString(), "Unable to Fetch Data From "+url);
//     }

// }
package sbury;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sbury.Products;
import sbury.SburyProducts;
import sbury.Product;

import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.nodes.Document;

public class TestSburyProducts {
    private Products products;
    private Product expectedProduct;

    @Before
    public void initiliseInputVariables() {
        EntityConfiguration.url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
   
        String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
        Document doc = JsoupHelper.getDocument(firstRecordUrl);
        expectedProduct = new SburyProduct(doc);
        expectedProduct.setEntities();

        products = new SburyProducts();
    }

    @Test
    public void testGet() {
      assertEquals(products.getProducts().get(0).getProduct().get("title")
      , expectedProduct.getProduct().get("title"));
      assertEquals(products.getProducts().get(0).getProduct().get("kcal_per_100g").toString()
      , expectedProduct.getProduct().get("kcal_per_100g").toString());
      assertEquals(products.getProducts().get(0).getProduct().get("description").toString()
      , expectedProduct.getProduct().get("description").toString());
      assertEquals(products.getProducts().get(0).getProduct().get("unit_price").toString()
      , expectedProduct.getProduct().get("unit_price").toString());
    }

}
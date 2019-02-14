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
    private Products productsMock;
    private Product expectedProduct;

    @Before
    public void initiliseInputVariables() {
        String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
        Document doc = JsoupHelper.getDocument(firstRecordUrl);
        expectedProduct = new SburyProduct(doc);
        expectedProduct.setEntities();

        productsMock = new SburyProducts();
    }

    @Test
    public void testGet() {
      assertEquals(productsMock.getProducts().get(0).getEntity("title")
      , expectedProduct.getEntity("title"));
      assertEquals(productsMock.getProducts().get(0).getEntity("kcal_per_100g").toString()
      , expectedProduct.getEntity("kcal_per_100g").toString());
      assertEquals(productsMock.getProducts().get(0).getEntity("description").toString()
      , expectedProduct.getEntity("description").toString());
      assertEquals(productsMock.getProducts().get(0).getEntity("unit_price").toString()
      , expectedProduct.getEntity("unit_price").toString());
    }

}
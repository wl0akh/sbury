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
import sbury.JsoupHelper;
import sbury.Entity;

import org.jsoup.nodes.Document;

import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestSburyProduct {
    private Product product;
    private Object expectedTitle;
    private Object expectedPricePerUnit;

    @Before
    public void initiliseInputVariables() {
        EntityConfiguration.url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
   
        String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
        Document doc = JsoupHelper.getDocument(firstRecordUrl);
        expectedTitle = doc.title();
        expectedPricePerUnit = JsoupHelper.roundItToTwoDecimals(
            JsoupHelper.getChileElements(doc, ".pricePerUnit")
            .get(0).ownText()
        );
        
        product = new SburyProduct(doc);
        product.setEntities();
    }

    @Test
    public void testGet() {
        assertEquals(product.getProduct().get("title"), expectedTitle);
        assertEquals(product.getProduct().get("unit_price"), expectedPricePerUnit);
    }

}
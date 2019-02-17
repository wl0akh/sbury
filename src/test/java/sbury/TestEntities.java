package sbury;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sbury.Entities;
import sbury.Entity;
import sbury.JsoupHelper;

public class TestEntities {
    @Test
    public void testSetDataValidUrl() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
        ArrayList<Map> records = new ArrayList<Map>();
        new Entities().setData(url, records);

        String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";

        String expectedTitle = JsoupHelper.getDocument(firstRecordUrl).title();
        Map hash = (Map) records.get(0);
        String actuialTitle = records.get(0).get("title").toString();
        assertEquals(expectedTitle, actuialTitle);
    }

    @Test
    public void testSetDataInvalidUrl() {
        String url = "**in_valid_url**";
        ArrayList<Map> records = new ArrayList<Map>();
        new Entities().setData(url, records);
        assertEquals(records.size(), 0);
    }

}
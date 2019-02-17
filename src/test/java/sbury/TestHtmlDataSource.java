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

import sbury.HtmlDataSource;
import sbury.Entity;
import sbury.JsoupHelper;

public class TestHtmlDataSource {
    @Test
    public void testGetRecordsValidUrl() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
        List<Entity> entities = EntityConfig.getEntities();
        HtmlDataSource htmlDataSource = new HtmlDataSource(url, entities);
        ArrayList records = (ArrayList) htmlDataSource.getRecords();

        String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";

        String expectedTitle = JsoupHelper.getDocument(firstRecordUrl).title();
        Map hash = (Map) records.get(0);
        String actuialTitle = hash.get("title").toString();
        assertEquals(expectedTitle, actuialTitle);
    }

    @Test
    public void testGetRecordsInvalidUrl() {
        String url = "**in_valid_url**";
        List<Entity> entities = EntityConfig.getEntities();
        HtmlDataSource htmlDataSource = new HtmlDataSource(url, entities);
        ArrayList records = (ArrayList) htmlDataSource.getRecords();
        assertEquals(records.size(), 0);
    }

}
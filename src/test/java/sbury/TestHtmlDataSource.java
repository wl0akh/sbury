package sbury;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import subry.Filter;
import subry.HtmlDataSource;

public class TestHtmlDataSource {
    @Test
    public void testGetRecords() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
       Filter filter = (element) -> (true);
        HtmlDataSource htmlDataSource = new HtmlDataSource();
        Class expectedArrayType = new ArrayList<Map<String, String>>().getClass();
        Class expectedElementType = new HashMap<String, String>().getClass();
        ArrayList records = (ArrayList) htmlDataSource.getRecords(url, filter);

        assertThat(records, instanceOf(expectedArrayType));
        assertThat(records.get(0), instanceOf(expectedElementType));
    }

}
// package sbury;

// import org.junit.Assert;
// import org.junit.Test;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static org.mockito.Matchers.anyString;

// import static org.hamcrest.CoreMatchers.instanceOf;
// import static org.hamcrest.CoreMatchers.is;
// import static org.junit.Assert.assertEquals;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;

// import sbury.DataSource;
// import sbury.Product;
// import sbury.JsoupHelper;

// public class TestHtmlDataSource {
//     @Test
//     public void testGetRecordsValidUrl() {
//         String firstRecordUrl ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
//         String expectedTitle = JsoupHelper.getDocument(firstRecordUrl).title();
//         String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
//         // Map<String, Entity> entityHash = EntityConfiguration.getEntityHash();
//         Product productMock = mock(Product.class);
//         Products productsMock = mock(Products.class);
//         DataSource DataSourceMock = new HtmlDataSource(url, productsMock);
//         when(productsMock.getProducts().get(0)).thenReturn(productMock);
//         when(productMock.getEntity("title")).thenReturn(expectedTitle);
//         Products products = DataSourceMock.getProducts();
//         Product product = products.getProducts().get(0);
//         String actuialTitle = product.getEntity("title").toString();
//         assertEquals(expectedTitle, actuialTitle);
//     }

//     // @Test
//     // public void testGetRecordsInvalidUrl() {
//     //     String url = "**in_valid_url**";
//     //     Map<String, Entity> entityHash = EntityConfiguration.getEntityHash();
//     //     HtmlDataSource htmlDataSource = new HtmlDataSource(url, entityHash);
//     //     ArrayList records = (ArrayList) htmlDataSource.getRecords();

//     //     String expectedTitle = "Unable to Fetch Data From "+url;
//     //     Map hash = (Map) records.get(0);
//     //     String actuialTitle = hash.get("ERROR").toString();
//     //     assertEquals(expectedTitle, actuialTitle);
//     // }

// }
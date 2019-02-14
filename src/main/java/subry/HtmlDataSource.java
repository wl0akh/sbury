package sbury;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlDataSource implements DataSource {
    private String url;
    // private Map<String, Product> entityHash;
    private Products products;



    public HtmlDataSource(String url, Products products) {
        this.url = url;
        this.products = products;
    }

    public Products getProducts() {
        // List<String> urls = geUrls(url);
        // for (String detailPageUrl : urls) {
        //     // Product product = new Product(JsoupHelper.getDocument(detailPageUrl), entityHash);// getProductInfoFromDocument(JsoupHelper.getDocument(detailPageUrl));
        //     products.add(product);
        // }

        return products;
    }

    private List<String> geUrls(String url) {
        List<String> urlArray = new ArrayList<String>();
        Document doc = JsoupHelper.getDocument(url);
        Elements elements = JsoupHelper.getChileElements((Element) doc, ".gridView .gridItem h3 a");
        for (Element element : elements) {
            urlArray.add(element.attr("abs:href"));
        }
        return urlArray;
    }

    // private Product getProcessedEntities(Document doc, Product entityHash) {
    //     Product processedEntityHash ;
    //     entityHash.forEach((key, entity) -> {
    //         // processedEntityHash.put(key, entity.getEntity(doc));
    //     });
    //     return processedEntityHash;
    // }
}
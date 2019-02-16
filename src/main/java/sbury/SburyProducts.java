package sbury;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;

public class SburyProducts implements Products {
    private List<String> urls;
    private List<Map> products;

    public SburyProducts() {
        this.products = new ArrayList<Map>();
        this.urls = EntityConfiguration.getUrls();
        urls.forEach(url -> addProductFromUrl(url));
    }

    public List<Map> getProducts() {
        return products;
    }

    private void addProductFromUrl(String url) {
        Document doc = JsoupHelper.getDocument(url);
        Product product = new SburyProduct(doc);
        product.setEntities();
        products.add(product.getProduct());
    }

}
package subry;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlDataSource implements DataSource {
    private String url;

    public HtmlDataSource(String url) {
        this.url = url;
    }

    public List<Map<String, String>> getRecords() {
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        List<String> urls = geUrls(url);
        for (String detailPageUrl : urls) {
            Map<String, String> productInfo = getProductInfoFromDocument(getDocument(detailPageUrl));
            records.add(productInfo);
        }

        return records;
    }

    private Elements getChileElements(Element parentElement, String ElementSelectorQuery) {
        Elements childElements = new Elements();
        try {
            childElements = parentElement.select(ElementSelectorQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return childElements;
    }

    private Document getDocument(String url) {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    private List<String> geUrls(String url) {
        List<String> urlArray = new ArrayList<String>();
        Document doc = getDocument(url);
        Elements elements = getChileElements((Element) doc, ".gridView .gridItem h3 a");
        for (Element element : elements) {
            urlArray.add(element.attr("abs:href"));
        }
        return urlArray;
    }

    private Map<String, String> getProductInfoFromDocument(Document doc) {
        Map<String, String> productInfo = new HashMap<String, String>();

        productInfo.put("title", doc.title());

        if (getChileElements(doc, ".nutritionTable").size() > 0) {
            Element nutritionTable = getChileElements(doc, ".nutritionTable").get(0);
            Element nutritionTableTbody = getChileElements(nutritionTable, "tbody").get(0);
            Element tbodySecondTr = getChileElements(nutritionTableTbody, "tr").get(1);
            Element trFirstTD = getChileElements(tbodySecondTr, "td").get(0);
            productInfo.put("kcal_per_100g", trFirstTD.ownText());
        }
        if (getChileElements(doc, "htmlcontent").size() > 0) {
            Element htmlcontent = getChileElements(doc, "htmlcontent").get(0);
            Element descriptionDiv = getChileElements(htmlcontent, "div.productText").get(0);
            productInfo.put("description", descriptionDiv.text());
        } else {
            Element informationDiv = getChileElements(doc, "div#information").get(0);
            Element itemTypeGroupContainerDiv = getChileElements(doc, "div.itemTypeGroupContainer").get(0);
            if (getChileElements(itemTypeGroupContainerDiv, "div.memo").size() > 0) {
                Element descriptionDiv = getChileElements(informationDiv, "div.memo").get(0);
                productInfo.put("description", descriptionDiv.text());
            } else {
                Element descriptionDiv = getChileElements(informationDiv, "div.itemTypeGroup").get(0);
                productInfo.put("description", descriptionDiv.text());
            }

        }

        Element pricePerUnitP = getChileElements(doc, ".pricePerUnit").get(0);
        productInfo.put("unit_price", pricePerUnitP.ownText());

        return productInfo;
    }

}
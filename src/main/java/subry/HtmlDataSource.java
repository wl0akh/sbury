package subry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlDataSource implements DataSource {
    public ArrayList<Map<String, String>> getRecords(String url, Filter filter) {
        ArrayList<Map<String, String>> records = new ArrayList<Map<String, String>>();
        ArrayList<String> urls = geUrls(url);
        for (String detailPageUrl : urls) {
            HashMap<String, String> productInfo = getProductInfoFromDocument(getDocument(detailPageUrl));
            if (filter.validate(productInfo))
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

    private ArrayList<String> geUrls(String url) {
        ArrayList<String> urlArray = new ArrayList<String>();
        Document doc = getDocument(url);
        Elements elements = getChileElements((Element) doc, ".gridView .gridItem h3 a");
        for (Element element : elements) {
            urlArray.add(element.attr("abs:href"));
        }
        return urlArray;
    }

    private HashMap<String, String> getProductInfoFromDocument(Document doc) {
        HashMap<String, String> productInfo = new HashMap<String, String>();

        productInfo.put("title", doc.title());

        if (getChileElements(doc, ".nutritionTable").size() > 0) {
            Element nutritionTable = getChileElements(doc, ".nutritionTable").get(0);
            Element nutritionTableTbody = getChileElements(nutritionTable, "tbody").get(0);
            Element tbodySecondTr = getChileElements(nutritionTableTbody, "tr").get(1);
            Element trFirstTD = getChileElements(tbodySecondTr, "td").get(0);
            productInfo.put("kcal_per_100g", trFirstTD.ownText());
        }

        Element pricePerUnitP = getChileElements(doc, ".pricePerUnit").get(0);
        productInfo.put("unit_price", pricePerUnitP.ownText());

        String descriptionMeta = getChileElements(doc, "meta[name=description]").get(0).attr("content");
        productInfo.put("description", descriptionMeta);

        return productInfo;
    }

}
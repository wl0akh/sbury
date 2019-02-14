package sbury;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EntityConfiguration {
    public static String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";

    public static List<String> getUrls() {
        List<String> urlArray = new ArrayList<String>();
        Document doc = JsoupHelper.getDocument(url);
        Elements elements = JsoupHelper.getChileElements((Element) doc, ".gridView .gridItem h3 a");
        for (Element element : elements) {
            urlArray.add(element.attr("abs:href"));
        }
        return urlArray;
    }

    public static Map<String, Entity> getEntityHash() {
        Map<String, Entity> entityHash = new HashMap<String, Entity>();
        Entity titleEntity = (String key, Document doc, Map<String, Object> productHash) -> {
            productHash.put(key, (Object) doc.title());
        };
        Entity pricePerUnit = (String key, Document doc, Map<String, Object> productHash) -> {
            Object pricePerUnitData = JsoupHelper.getChileElements(doc, ".pricePerUnit")
            .get(0).ownText();
            productHash.put(key, (Object) JsoupHelper.roundItToTwoDecimals(pricePerUnitData));
        };
        entityHash.put("title", titleEntity);
        entityHash.put("kcal_per_100g", getKCalPerHundredGram());
        entityHash.put("unit_price", pricePerUnit);
        entityHash.put("description", getDescription());
        return entityHash;
    }

    public static Entity getKCalPerHundredGram() {
        Entity entity = (String key, Document doc, Map<String, Object> productHash) -> {
            String kCalPerHundredGram = "";
            if (JsoupHelper.getChileElements(doc, ".nutritionTable").size() > 0) {
                Element nutritionTable = JsoupHelper.getChileElements(doc, ".nutritionTable").get(0);
                Element nutritionTableTbody = JsoupHelper.getChileElements(nutritionTable, "tbody").get(0);
                Element tbodySecondTr = JsoupHelper.getChileElements(nutritionTableTbody, "tr").get(1);
                Element trFirstTD = JsoupHelper.getChileElements(tbodySecondTr, "td").get(0);
                kCalPerHundredGram = trFirstTD.ownText();
                productHash.put(key, (Object) JsoupHelper.stringToNumber(kCalPerHundredGram));
            }
        };
        return entity;
    }

    public static Entity getDescription() {
        Entity entity = (String key, Document doc, Map<String, Object> productHash) -> {
            String description = "";
            if (JsoupHelper.getChileElements(doc, "htmlcontent").size() > 0) {
                Element htmlcontent = JsoupHelper.getChileElements(doc, "htmlcontent").get(0);
                Element descriptionDiv = JsoupHelper.getChileElements(htmlcontent, "div.productText").get(0);
                description = descriptionDiv.text();
                productHash.put(key, (Object) description);
            } else {
                Element informationDiv = JsoupHelper.getChileElements(doc, "div#information").get(0);
                Element itemTypeGroupContainerDiv = JsoupHelper.getChileElements(doc, "div.itemTypeGroupContainer")
                        .get(0);
                if (JsoupHelper.getChileElements(itemTypeGroupContainerDiv, "div.memo").size() > 0) {
                    Element descriptionDiv = JsoupHelper.getChileElements(informationDiv, "div.memo").get(0);
                    description = descriptionDiv.text();
                    productHash.put(key, (Object) description);
                } else {
                    Element descriptionDiv = JsoupHelper.getChileElements(informationDiv, "div.itemTypeGroup").get(0);
                    description = descriptionDiv.text();
                    productHash.put(key, (Object) description);
                }
            }
        };
        return entity;
    }

}
package sbury;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHelper{

    public static Elements getChileElements(Element parentElement, String ElementSelectorQuery) {
        Elements childElements = new Elements();
        try {
            childElements = parentElement.select(ElementSelectorQuery);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return childElements;
    }

    public static Document getDocument(String url) {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return doc;
    }

}
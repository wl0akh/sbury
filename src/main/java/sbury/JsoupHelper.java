package sbury;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.logging.Logger;

public class JsoupHelper {

    private final static Logger LOGGER = Logger.getLogger(JsoupHelper.class.getName());

    public static Elements getChileElements(Element parentElement, String ElementSelectorQuery) {
        Elements childElements = new Elements();
        try {
            childElements = parentElement.select(ElementSelectorQuery);
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return childElements;
    }

    public static Document getDocument(String url) {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return doc;
    }

    public static double stringToNumber(Object value) {
        double number = 0;
        try {
            number = Double.parseDouble(value.toString().replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return number;
    }

    public static double roundItToTwoDecimals(Object value) {
        double roundOff = 0.00;
        try {
            roundOff = (double) Math.round(stringToNumber(value) * 100) / 100;
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return roundOff;
    }
}
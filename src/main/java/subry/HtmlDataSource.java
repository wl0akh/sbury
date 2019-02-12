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
    private Map<String, Entity> entityHash;

    public HtmlDataSource(String url, Map<String, Entity> entityHash) {
        this.url = url;
        this.entityHash = entityHash;
    }

    public List<Map<String, String>> getRecords() {
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        List<String> urls = geUrls(url);
        if(urls.size() == 0){
            String errorText = "Unable to Fetch Data From "+url;
            Map<String, String> error = new HashMap<String, String>();
            error.put("ERROR", errorText);
            records.add(error);
        }
        for (String detailPageUrl : urls) {
            Map<String, String> productInfo = getProcessedEntities(JsoupHelper.getDocument(detailPageUrl), entityHash);
            records.add(productInfo);
        }

        return records;
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

    private Map<String, String> getProcessedEntities(Document doc, Map<String, Entity> entityHash) {
        Map<String, String> processedEntityHash = new HashMap<String, String>();
        entityHash.forEach((key, entity) -> {
            processedEntityHash.put(key, entity.getData(doc));
        });
        return processedEntityHash;
    }
}

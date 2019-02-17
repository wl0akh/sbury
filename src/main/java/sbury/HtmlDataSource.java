package sbury;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.logging.Logger;

public class HtmlDataSource implements DataSource {
    private String url;
    private List<Entity> entitys;
    private final static Logger LOGGER = Logger.getLogger(HtmlDataSource.class.getName());


    public HtmlDataSource(String url, List<Entity> entitys) {
        this.url = url;
        this.entitys = entitys;
    }

    public List<Map> getRecords() {
        List<Map> records = new ArrayList<Map>();
        List<String> urls = geUrls(url);
        urls.forEach(url -> {
            Document detailPageDoc = JsoupHelper.getDocument(url);
            Map<String, Object> productInfo = getProcessedEntities(detailPageDoc, entitys);
            records.add(productInfo);
        });

        return records;
    }

    private List<String> geUrls(String url) {
      List<String> urlArray = new ArrayList<String>();
        try{
          Document doc = JsoupHelper.getDocument(url);
          Elements elements = JsoupHelper.getChileElements((Element) doc, ".gridView .gridItem h3 a");
          elements.forEach(element -> {urlArray.add(element.attr("abs:href"));});
        }catch(Exception e){
          LOGGER.warning("ERROR: " + e.getMessage());
        }
        return urlArray;
    }

    private Map<String, Object> getProcessedEntities(Document doc, List<Entity> entitys) {
        Map<String, Object> processedEntityHash = new HashMap<String, Object>();
        entitys.forEach((entity) -> {entity.setData(doc, processedEntityHash);});
        return processedEntityHash;
    }
}

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

public class Entities implements Entity<Document,List> {
    
    private final static Logger LOGGER = Logger.getLogger(Entities.class.getName());

    public void setData(Document doc, List records){
        try{
            List<String> urls = getUrls(doc);
            urls.forEach(url -> {
                Document detailPageDoc = JsoupHelper.getDocument(url);
                Map<String, Object> productInfo = getProcessedEntities(detailPageDoc, getEntities());
                records.add(productInfo);
            });
        }catch(Exception e){
            LOGGER.warning("ERROR: " + e.getMessage());
        }
    }

    private List<String> getUrls(Document doc) {
        List<String> urlArray = new ArrayList<String>();
          try{
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

    private List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(EntityConfig.getTitleEntity());
        entities.add(EntityConfig.getKCalPerHundredGram());
        entities.add(EntityConfig.getPricePerUnit());
        entities.add(EntityConfig.getDescription());
        return entities;
    }

}
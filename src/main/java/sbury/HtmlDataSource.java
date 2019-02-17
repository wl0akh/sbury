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
    private Entity entitys;
    private final static Logger LOGGER = Logger.getLogger(HtmlDataSource.class.getName());


    public HtmlDataSource(String url, Entity entitys) {
        this.url = url;
        this.entitys = entitys;
    }

    public List<Map> getRecords() {
      List<Map> records = new ArrayList<Map>();
      try{
        entitys.setData(url, records);
      }catch(Exception e){
          LOGGER.warning("ERROR: " + e.getMessage());
      }
        return records;
    }
}

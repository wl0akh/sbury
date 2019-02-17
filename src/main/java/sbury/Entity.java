package sbury;

import org.jsoup.nodes.Document;
import java.util.Map;

public interface Entity {

  abstract void setData(Document doc, Map<String, Object> productHash);
  
}
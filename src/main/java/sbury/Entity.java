package sbury;

import org.jsoup.nodes.Document;
import java.util.Map;

public interface Entity<U,T> {

  abstract void setData(U doc, T record);
  
}
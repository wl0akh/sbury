package sbury;

import org.jsoup.nodes.Document;

public interface Product {
    public Object getEntity(String htmlEntity);
    public void setEntities();
}
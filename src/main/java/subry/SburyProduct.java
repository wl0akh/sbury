package sbury;

import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.Map;

public class SburyProduct implements Product {
    public Map<String, Object> productHash;
    private Map<String, Entity> entityHash;
    private Document doc;

    public SburyProduct(Document doc){
        this.entityHash = EntityConfiguration.getEntityHash();
        this.doc = doc;
        this.productHash = new HashMap<String, Object>();
    }

    public void setEntities() {
        entityHash.forEach((key, entity) -> {
            entity.setData(key, doc, productHash);
        });
    }

    public Object getEntity(String htmlEntity) {
        return productHash.get(htmlEntity);
    }
}
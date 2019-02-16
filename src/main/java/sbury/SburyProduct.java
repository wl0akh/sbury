package sbury;

import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SburyProduct implements Product {
    private final static Logger LOGGER = Logger.getLogger(SburyProduct.class.getName());
    public Map<String, Object> productHash;
    private Map<String, Entity> entityHash;
    private Document doc;

    public SburyProduct(Document doc) {
        this.entityHash = EntityConfiguration.getEntityHash();
        this.doc = doc;
        this.productHash = new HashMap<String, Object>();
    }

    public void setEntities() {
        entityHash.forEach((key, entity) -> {
            try {
                entity.setData(key, doc, productHash);
            } catch (Exception e) {
                LOGGER.warning("ERROR: " + e.getMessage());
            }
        });
    }

    public Map<String, Object> getProduct() {
        return productHash;
    }
}
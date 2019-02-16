package sbury;

import java.util.Map;
import java.util.logging.Logger;
import java.util.Comparator;

public class Console {

    private final static Logger LOGGER = Logger.getLogger(Console.class.getName());

    public static void main(String[] args) {
        try {
            EntityConfiguration.url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
            Filter allowAll = (e) -> (true);
            Sort sortBy = (p1,p2)->(1);
            Products products = new SburyProducts();
            JsonViewProcessor jsonViewProcessor = new JsonViewProcessor(products, allowAll, sortBy);
            FetchService fetchService = new FetchService(jsonViewProcessor);
            System.out.println(fetchService.fetch());
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
    }
}

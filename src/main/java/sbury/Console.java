package sbury;

import java.util.Map;
import java.util.logging.Logger;
import java.util.Comparator;

public class Console {

    private final static Logger LOGGER = Logger.getLogger(Console.class.getName());

    public static void main(String[] args) {
        try {
            Filter allowAll = (e) -> (true);
            Sort sortBy = (p1,p2)->(1);
            DataSource htmlDataSource = new HtmlDataSource(EntityConfig.url, EntityConfig.getEntities());
            JsonViewProcessor jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll, sortBy);
            FetchService fetchService = new FetchService(jsonViewProcessor);
            System.out.println(fetchService.fetch());
        } catch (Exception e) {
            LOGGER.warning("ERROR: " + e.getMessage());
        }
    }
}

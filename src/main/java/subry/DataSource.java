package subry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@FunctionalInterface
public interface DataSource {
    abstract ArrayList<Map<String, String>> getRecords(String url, Filter filter);
}
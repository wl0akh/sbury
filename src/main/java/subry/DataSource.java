package subry;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface DataSource {
    abstract List<Map<String, String>> getRecords();
}
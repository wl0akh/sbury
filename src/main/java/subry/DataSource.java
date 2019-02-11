package sbury;

import java.util.List;
import java.util.Map;

public interface DataSource {
    abstract List<Map<String, String>> getRecords();
}
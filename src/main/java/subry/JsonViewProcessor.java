package subry;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonViewProcessor implements ViewProcessor {
    private DataSource dataSource;
    private Filter filter;

    public JsonViewProcessor(DataSource dataSource, Filter filter) {
        this.dataSource = dataSource;
        this.filter = filter;
    }

    public String process() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Map<String, String>> records = dataSource.getRecords();
        List<Map<String, Object>> formatedRecords = getFormatedRecords(records);
        Double gross = formatedRecords.stream().mapToDouble(map -> ((Double) map.get("unit_price"))).sum();
        return gson.toJson(getJsonHash(formatedRecords, gross));
    }

    private Map<String, Object> getJsonHash(List<Map<String, Object>> formatedRecords, Double gross) {
        Double roundedGross = roundItToTwoDecimals(gross);
        Double roundedVat = roundItToTwoDecimals(gross * 0.2);
        Map<String, Double> total = new HashMap<String, Double>();
        total.put("gross", roundedGross);
        total.put("vat", roundedVat);
        Map<String, Object> jsonHash = new HashMap<String, Object>();
        jsonHash.put("results", formatedRecords);
        jsonHash.put("total", total);
        return jsonHash;
    }

    private double stringToNumber(Object value) {
        double number = 0;
        try {
            number = Double.parseDouble(value.toString().replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
        }
        return number;
    }

    public static double roundItToTwoDecimals(double value) {
        double roundOff = 0.00;
        try {
            roundOff = (double) Math.round(value * 100) / 100;
        } catch (Exception e) {
        }
        return roundOff;
    }

    private List<Map<String, Object>> getFormatedRecords(List<Map<String, String>> records) {
        List<Map<String, Object>> formatedRecords = new ArrayList<Map<String, Object>>();
        for (Map<String, String> record : records) {
            Map<String, Object> hash = new HashMap<String, Object>();
            hash.put("title", record.get("title").toString().replaceAll("[-+.^:,']", ""));
            hash.put("description", record.get("description").toString());
            hash.put("kcal_per_100g", stringToNumber(record.get("kcal_per_100g")));
            hash.put("unit_price", stringToNumber(record.get("unit_price")));
            formatedRecords.add(hash);
        }
        return formatedRecords;
    }
}
package sbury;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonViewProcessor implements ViewProcessor {
    private DataSource htmlDataSource;
    private Filter filter;
    private Sort sortBy;
    private final static Logger LOGGER = Logger.getLogger(JsonViewProcessor.class.getName());

    public JsonViewProcessor(DataSource htmlDataSource, Filter filter, Sort sortBy) {
        this.htmlDataSource = htmlDataSource;
        this.filter = filter;
        this.sortBy = sortBy;
    }

    public String process() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Map> productsArray = new ArrayList<Map>();
        Double gross = 0.0;
        try{
            productsArray = htmlDataSource.getRecords();

            productsArray.stream()
            .filter(filter)
            .collect(Collectors.toList()); 

            productsArray.sort(sortBy);

            gross = productsArray.stream().mapToDouble(map -> ((Double) map.get("unit_price"))).sum();
        }catch(Exception e){
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return gson.toJson(getJsonHash(productsArray, gross));
    }

    private Map<String, Object> getJsonHash(List<Map> productsArray, Double gross) {
        Double roundedGross = JsoupHelper.roundItToTwoDecimals(gross);
        Double roundedVat = JsoupHelper.roundItToTwoDecimals(gross * 0.2);
        Map<String, Double> total = new HashMap<String, Double>();
        total.put("gross", roundedGross);
        total.put("vat", roundedVat);
        Map<String, Object> jsonHash = new HashMap<String, Object>();
        jsonHash.put("results", productsArray);
        jsonHash.put("total", total);
        return jsonHash;
    }
}
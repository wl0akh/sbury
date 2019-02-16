package sbury;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbury.Product;

public class JsonViewProcessor implements ViewProcessor {
    private Products products;
    private Filter filter;
    private Sort sortBy;

    public JsonViewProcessor(Products products, Filter filter, Sort sortBy) {
        this.products = products;
        this.filter = filter;
        this.sortBy = sortBy;
    }

    public String process() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Map> filteredProductsArray = products.getProducts();

        filteredProductsArray.stream()
        .filter(filter)
        .collect(Collectors.toList()); 

        filteredProductsArray.sort(sortBy);

        Double gross = filteredProductsArray.stream().mapToDouble(map -> ((Double) map.get("unit_price"))).sum();
        return gson.toJson(getJsonHash(filteredProductsArray, gross));
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
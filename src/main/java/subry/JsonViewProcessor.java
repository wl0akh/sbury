// package sbury;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;

// public class JsonViewProcessor implements ViewProcessor {
//     private DataSource dataSource;
//     private Filter filter;

//     public JsonViewProcessor(DataSource dataSource, Filter filter) {
//         this.dataSource = dataSource;
//         this.filter = filter;
//     }

//     public String process() {
//         Gson gson = new GsonBuilder().setPrettyPrinting().create();
//         Products products = dataSource.getProducts();
//         // if(products.get(0).containsKey("ERROR")){
//         //   Map<String, String> error = new HashMap<String, String>();
//         //   error.put("ERROR", products.get(0).get("ERROR").toString());
//         //   return gson.toJson(error);
//         // }
//         Products formatedRecords = getFormatedRecords(products);
//         // Double gross = formatedRecords.stream().mapToDouble(map -> ((Double) map.get("unit_price"))).sum();
//         return"";// gson.toJson(getJsonHash(formatedRecords, gross));
//     }

//     private Map<String, Object> getJsonHash(Products formatedRecords, Double gross) {
//         Double roundedGross = roundItToTwoDecimals(gross);
//         Double roundedVat = roundItToTwoDecimals(gross * 0.2);
//         Map<String, Double> total = new HashMap<String, Double>();
//         total.put("gross", roundedGross);
//         total.put("vat", roundedVat);
//         Map<String, Object> jsonHash = new HashMap<String, Object>();
//         jsonHash.put("results", formatedRecords);
//         jsonHash.put("total", total);
//         return jsonHash;
//     }

//     private double stringToNumber(Object value) {
//         double number = 0;
//         try {
//             number = Double.parseDouble(value.toString().replaceAll("[^\\d.]", ""));
//         } catch (Exception e) {
//         }
//         return number;
//     }

//     public static double roundItToTwoDecimals(double value) {
//         double roundOff = 0.00;
//         try {
//             roundOff = (double) Math.round(value * 100) / 100;
//         } catch (Exception e) {
//         }
//         return roundOff;
//     }

//     private Products getFormatedRecords(Products products) {
//         Products formatedRecords;
//         // for (Products record : products) {
//         //     Product product = new HashMap<String, Object>();
//         //     record.forEach((key, value) -> {
//         //         hash.put(key, value);
//         //     });
//         //     hash.put("kcal_per_100g", stringToNumber(record.get("kcal_per_100g")));
//         //     hash.put("unit_price", roundItToTwoDecimals(stringToNumber(record.get("unit_price"))));
//         //     formatedRecords.add(hash);
//         // }
//         return formatedRecords;
//     }
// }
// package sbury;

// import java.util.Map;

// public class Console {
//     public static void main(String[] args) {
//         try {
//             String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
//             Map<String, Product> entityHash = EntityConfiguration.getEntityHash();
//             HtmlDataSource htmlDataSource = new HtmlDataSource(url, entityHash);
//             Filter allowAll = (e) -> (true);
//             JsonViewProcessor jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll);
//             FetchService fetchService = new FetchService(jsonViewProcessor);
//             System.out.println(fetchService.fetch());
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

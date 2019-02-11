package subry;

public class Console {
    public static void main(String[] args) {
        try {
            String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/berries-cherries-currants.html";
            HtmlDataSource htmlDataSource = new HtmlDataSource(url);
            Filter allowAll = (e) -> (true);
            JsonViewProcessor jsonViewProcessor = new JsonViewProcessor(htmlDataSource, allowAll);
            FetchService fetchService = new FetchService(jsonViewProcessor);
            System.out.println(fetchService.fetch());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

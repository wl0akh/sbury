package subry;

public class FetchService {
    private ViewProcessor viewProcessor;

    public FetchService(ViewProcessor viewProcessor) {
        this.viewProcessor = viewProcessor;
    }

    public String fetch() {
        return viewProcessor.process();
    }
}

package sbury;

import java.util.logging.Logger;

public class FetchService {
    private ViewProcessor viewProcessor;
    private final static Logger LOGGER = Logger.getLogger(FetchService.class.getName());

    public FetchService(ViewProcessor viewProcessor) {
        this.viewProcessor = viewProcessor;
    }

    public String fetch() {
        String result = "{}";
        try{
            result = viewProcessor.process();
        }catch(Exception e){
            LOGGER.warning("ERROR: " + e.getMessage());
        }
        return result;
    }
}

package hust.ite6.group11.main.scraper;

import hust.ite6.group11.main.exceptions.NullConfigException;
import hust.ite6.group11.main.utils.Config;

import java.io.IOException;

public class ScraperFactory {
    private ScraperFactory() {}

    public static Scraper getScraper(ScraperType type) throws IOException, NullConfigException {
        final Config CONFIG = Config.getInstance();
        switch (type) {
            case TWITTER -> {
                return new TwitterScraper(
                        CONFIG.getTwitterUsername(),
                        CONFIG.getTwitterPassword(),
                        CONFIG.getChromeDriverPath(),
                        CONFIG.getChromePath());
            }

            case AIRNTFS -> {
                return new AirNFTsScraper(
                        CONFIG.getChromeDriverPath(),
                        CONFIG.getChromePath());
            }

            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}

package nftspy.scraper;

import nftspy.exceptions.NullConfigException;
import nftspy.utils.Config;

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

            case NFTICALLY -> {
                return new NFTicallyScraper(
                        CONFIG.getChromeDriverPath(),
                        CONFIG.getChromePath());
            }

            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}

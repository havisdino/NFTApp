package test;

import nftspy.post.Post;
import nftspy.scraper.AirNFTsScraper;
import nftspy.scraper.Scraper;

import java.util.List;

public class AirNTFsScraperTest {
    public static void main(String[] args) {
        Scraper sc = new AirNFTsScraper(
                "/Users/havisdino/Code/Java/Libraries/ChromeDriver/c105/chromedriver",
                "/Applications/Chromium.app/Contents/MacOS/Chromium");

        List<Post> posts = sc.browse();
    }
}

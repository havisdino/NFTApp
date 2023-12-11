package hust.ite6.group11.test;

import hust.ite6.group11.post.Post;
import hust.ite6.group11.scraper.AirNFTsScraper;
import hust.ite6.group11.scraper.Scraper;

import java.util.List;

public class AirNTFsScraperTest {
    public static void main(String[] args) {
        Scraper sc = new AirNFTsScraper(
                "/Users/havisdino/Code/Java/Libraries/ChromeDriver/c105/chromedriver",
                "/Applications/Chromium.app/Contents/MacOS/Chromium",
                true);

        List<Post> posts = sc.browse();

    }
}

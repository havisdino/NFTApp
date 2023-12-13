package hust.ite6.group11.test;

import hust.ite6.group11.main.post.Post;
import hust.ite6.group11.main.scraper.AirNFTsScraper;
import hust.ite6.group11.main.scraper.Scraper;

import java.util.List;

public class AirNTFsScraperTest {
    public static void main(String[] args) {
        Scraper sc = new AirNFTsScraper(
                "/Users/havisdino/Code/Java/Libraries/ChromeDriver/c105/chromedriver",
                "/Applications/Chromium.app/Contents/MacOS/Chromium");

        List<Post> posts = sc.browse();
    }
}

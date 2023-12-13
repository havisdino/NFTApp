package hust.ite6.group11.test;

import hust.ite6.group11.main.post.Post;
import hust.ite6.group11.main.scraper.Scraper;
import hust.ite6.group11.main.scraper.TwitterScraper;

import java.util.List;

public class TwitterScraperTest {
    public static void main(String[] args) {
        Scraper scraper = new TwitterScraper(
                "dinosaur9x2",
                "dadinosaur@1803!",
                "/Users/havisdino/Code/Java/Libraries/ChromeDriver/c105/chromedriver",
                "/Applications/Chromium.app/Contents/MacOS/Chromium");
        List<Post> postList = scraper.browse();

        for (Post post : postList) {
            System.out.println(post.getContent());
        }
    }
}

package hust.ite6.group11.main.scraper;

import hust.ite6.group11.main.post.Post;

import java.util.List;

public class OpenSeaScraper extends Scraper {
    public OpenSeaScraper(String chromeDriverPath, String chromePath) {
        super(chromeDriverPath, chromePath);
    }

    @Override
    public List<Post> browse() {
        return null;
    }
}

package hust.ite6.group11.main.scraper;

import hust.ite6.group11.main.post.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AirNFTsScraper extends Scraper {
    private static final int MAX_POSTS_FETCHED = 10;

    public AirNFTsScraper(String chromeDriverPath, String chromePath) {
        super(chromeDriverPath, chromePath);
    }

    private List<String> getPostURLs() {
        getDriver().get("https://www.airnfts.com/blog");
        WebElement list = getDriver().findElement(By.xpath("//div[@role='list']"));
        List<WebElement> postThumbnails = list.findElements(By.tagName("a"));
        List<String> postURLs = new ArrayList<>();
        for (WebElement e : postThumbnails) {
            String url = e.getAttribute("href");
            postURLs.add(url);
        }
        return postURLs;
    }

    @Override
    public List<Post> browse() {
        System.out.print("Initializing ... ");
        List<String> postURLs = getPostURLs();
        System.out.println("Done");

        List<Post> posts = new ArrayList<>();

        for (String url : postURLs.subList(0, AirNFTsScraper.MAX_POSTS_FETCHED)) {
            getDriver().get(url);
            WebElement titleElement = getDriver().findElement(By.className("blog-header-h1"));
            String title = titleElement.getText();
            WebElement articleElement = getDriver().findElement(By.tagName("article"));
            String content = articleElement.getText();

            posts.add(new Post(title, content, url));
        }
        return posts;
    }
}

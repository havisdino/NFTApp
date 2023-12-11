package hust.ite6.group11.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import hust.ite6.group11.post.Post;

import java.util.ArrayList;
import java.util.List;

public class AirNFTsScraper extends Scraper {
    public AirNFTsScraper(String chromeDriverPath, String chromePath) {
        super(chromeDriverPath, chromePath);
    }

    public AirNFTsScraper(String chromeDriverPath, String chromePath, boolean headless) {
        super(chromeDriverPath, chromePath, headless);
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
        List<String> postURLs = getPostURLs();
        List<Post> posts = new ArrayList<>();

        for (String url : postURLs) {
            getDriver().get(url);
            WebElement titleElement = getDriver().findElement(By.className("blog-header-h1"));
            String title = titleElement.getText();
            WebElement articleElement = getDriver().findElement(By.tagName("article"));
            String content = articleElement.getText();

            posts.add(new Post(title, content, url));
            System.out.println(content);
        }

        return posts;
    }
}

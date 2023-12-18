package nftspy.scraper;

import nftspy.post.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NFTicallyScraper extends Scraper {
    public NFTicallyScraper(String chromeDriverPath, String chromePath) {
        super(chromeDriverPath, chromePath);
    }

    @Override
    public List<Post> browse() {
        System.out.println(prefix + "Initilizing ... ");
        getDriver().get("https://www.nftically.com/blog/");
        System.out.println(prefix + "Done");
        List<WebElement> elements = getDriver().findElements(By.className("blog-title"));
        List<String> urls = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        for (WebElement e : elements) {
            urls.add(e.findElement(By.tagName("a")).getAttribute("href"));
        }
        for (String url : urls) {
            getDriver().get(url);
            String title = getDriver().findElement(By.className("page-title")).getText();
            String content = getDriver().findElement(By.className("blog-detail-wrap")).getText();
            List<String> tags = new ArrayList<>();
            for (WebElement e : getDriver().findElements(By.className("saspot-label"))) {
                tags.add("#" + e.getText());
            }
            posts.add(new Post(url, title, content, tags));
        }
        return posts;
    }
}

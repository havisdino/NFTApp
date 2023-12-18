// Dinh Viet Ha - 20215042

package nftspy.scraper;

import nftspy.post.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TwitterScraper extends Scraper {
    private final String email;
    private final String password;

    public TwitterScraper(String email, String password, String chromeDriverPath, String chromePath) {
        super(chromeDriverPath, chromePath);
        this.email = email;
        this.password = password;
    }

    private void logIn() {
        getDriver().get("https://twitter.com/login");
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement accForm = getDriver().findElement(By.xpath("//input[@name='text']"));
        accForm.sendKeys(email);

        WebElement nextButton = getDriver().findElement(By.xpath("//span[contains(text(),'Next')]"));
        nextButton.click();

        WebElement passwordForm = getDriver().findElement(By.xpath("//input[@name='password']"));
        passwordForm.sendKeys(password);

        WebElement signInButton = getDriver().findElement(By.xpath("//span[contains(text(),'Log in')]"));
        signInButton.click();
    }

    private void search(String query) {
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@aria-label='Search query']"));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Override
    public List<Post> browse() {
        System.out.print("Logging in ... ");
        logIn();
        System.out.println("\\u001B[32m Done");
        search("NFT");

        final int scrollCount = 10;
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < scrollCount; i++) {
            getActions().sendKeys(Keys.DOWN);
            List<WebElement> tweetElements = getDriver().findElements(By.cssSelector("article"));
            for (WebElement tweetElement : tweetElements) {
                String postUrl = tweetElement.getAttribute("href");

                getDriver().get(postUrl);
                WebElement contentElement = getDriver().findElement(By.xpath("//*[@id='id__28l89zgfbh3']"));
                String content = contentElement.getText();
                postList.add(new Post(postUrl, null, content));
            }
        }
        return postList;
    }
}

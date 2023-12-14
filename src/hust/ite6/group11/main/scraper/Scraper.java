// Dinh Viet Ha - 20215042

package hust.ite6.group11.main.scraper;

import hust.ite6.group11.main.post.Post;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class Scraper {
    private final WebDriver driver;
    private final Actions actions;

    public Scraper(String chromeDriverPath, String chromePath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.setBinary(chromePath);
        driverOptions.addArguments("headless");

        driverOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(driverOptions);
        actions = new Actions(driver);
    }

    public Actions getActions() {
        return actions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public abstract List<Post> browse();

    public void close() {
        String prefix = "[" + this.getClass().getSimpleName() + "] ";
        System.out.println(prefix + "Browser driver closed");
        driver.quit();
    }
}

package Utility;

import org.openqa.selenium.WebDriver;

public class BrowserDriver {
    protected final WebDriver driver;

    public BrowserDriver(WebDriver driver){
        this.driver = driver;
        System.setProperty("webdriver.chrome.driver ", "src/main/resources/chromedriver.exe");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
}

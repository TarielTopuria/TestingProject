package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPageObject {
    @FindBy(id = "search")
    protected WebElement searchBar;

    @FindBy(className = "page-title")
    protected WebElement searchResult;
}
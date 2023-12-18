package Steps;

import Data.ICommon;
import Pages.LandingPageObject;
import Utility.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPageSteps extends LandingPageObject implements ICommon {
    BrowserDriver browserDriver;
    public LandingPageSteps(WebDriver driver) {
        browserDriver = new BrowserDriver(driver);
        PageFactory.initElements(driver, this);
    }

    @Step
    public void enterSearchText(String text) {
        searchBar.click();
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
    }

    @Step
    public String getSearchLabelPlaceholder() {
        return searchBar.getAttribute("placeholder");
    }
    @Step
    public String getSearchResult() {
        return searchResult.getText();
    }
}

import Data.ICommon;
import Data.ILandingPageData;
import Steps.LandingPageSteps;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LandingPageTests implements ICommon, ILandingPageData {
    WebDriver driver = new ChromeDriver();
    private final LandingPageSteps steps = new LandingPageSteps(driver);

    @BeforeClass
    public void launchURL() {
        driver.get(BaseURL);
    }

    @AfterClass
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test (priority = 1)
    @Description("Check search label placeholder value")
    public void searchLabelExist(){
       assertEquals(steps.getSearchLabelPlaceholder(), searchLabelPlaceholderText, "Placeholder is not correct");
    }

    @Test (priority = 2)
    @Description("Check search function")
    public void searchText(){
        steps.enterSearchText("Crucial MX500 500GB Sata 3");
        assertTrue(steps.getSearchResult().contains(searchObjectName), "Search result doesn't contain search text");
    }
}

import Data.ICommon;
import Data.IRegistrationPageData;
import Steps.RegistrationPageSteps;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationPageTests implements ICommon, IRegistrationPageData {
    WebDriver driver = new ChromeDriver();
    Faker faker = new Faker();
    private final RegistrationPageSteps steps = new RegistrationPageSteps(driver);

    @BeforeClass
    public void launchURL() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        driver.get(BaseURL + RegistrationPageUrl);
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @BeforeMethod
    public void refreshPage(){
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    @Description("Positive Case: Check if user type field is displayed")
    public void checkUserTypeField(){
        Assert.assertTrue(steps.getUserType().isDisplayed(), "User type field is not displayed");
    }

    @Test(priority = 2)
    @Description("Positive Case: Check if email field is displayed")
    public void checkEmailField(){
        Assert.assertTrue(steps.getEmailElement().isDisplayed(), "Email field is not displayed");
    }

    @Test(priority = 3)
    @Description("Positive Case: Check if password field is displayed")
    public void checkPasswordField(){
        Assert.assertTrue(steps.getPasswordElement().isDisplayed(), "Password field is not displayed");
    }

    @Test(priority = 4)
    @Description("Positive Case: Check if password confirmation field is displayed")
    public void checkPasswordConfirmationField(){
        Assert.assertTrue(steps.getPasswordConfirmationElement().isDisplayed(), "Password Confirmation field is not displayed");
    }

    @Test(priority = 5)
    @Description("Positive Case: Check if firstname field is displayed")
    public void checkFirstnameField(){
        Assert.assertTrue(steps.getFirstNameElement().isDisplayed(), "Firstname field is not displayed");
    }

    @Test(priority = 6)
    @Description("Positive Case: Check if lastname field is displayed")
    public void checkLastnameField(){
        Assert.assertTrue(steps.getLastNameElement().isDisplayed(), "Lastname field is not displayed");
    }

    @Test(priority = 7)
    @Description("Negative Case: Check registration without required fields")
    public void checkRegistrationWithoutRequiredFields(){
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getEmailErrorMessage(), "ველი 'ელ-ფოსტა' აუცილებელია", "Email error message is not correct");
        Assert.assertEquals(steps.getPasswordErrorMessage(), "ველი 'პაროლი' აუცილებელია", "Password error message is not correct");
        Assert.assertEquals(steps.getPassConfirmationErrorMessage(), "ველი 'დაადასტურეთ პაროლი' აუცილებელია", "Password Confirmation error message is not correct");
    }

    @Test(priority = 8)
    @Description("Negative Case: Check registration without email field")
    public void checkRegistrationWithoutEmail(){
        String password = faker.internet().password();
        steps.enterPassword(password);
        steps.confirmPassword(password);
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getEmailErrorMessage(), "ველი 'ელ-ფოსტა' აუცილებელია", "Email error message is not correct");
    }

    @Test(priority = 9)
    @Description("Negative Case: Check registration without password field")
    public void checkRegistrationWithoutPassword(){
        steps.enterEmail(faker.internet().emailAddress());
        steps.confirmPassword(faker.internet().password());
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getPasswordErrorMessage(), "ველი 'პაროლი' აუცილებელია", "Password error message is not correct");
    }

    @Test(priority = 10)
    @Description("Negative Case: Check registration without password confirmation field")
    public void checkRegistrationWithoutPasswordConfirmation(){
        steps.enterEmail(faker.internet().emailAddress());
        steps.enterPassword(faker.internet().password());
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getPassConfirmationErrorMessage(), "ველი 'დაადასტურეთ პაროლი' აუცილებელია", "Password Confirmation error message is not correct");
    }

    @Test(priority = 11)
    @Description("Negative Case: Check registration with email without symbol")
    public void checkRegistrationWithEmailWithoutSymbol(){
        String password = faker.internet().password(6, 10, true, true, true);
        steps.enterEmail(emailWithoutSymbol);
        steps.enterPassword(password);
        steps.confirmPassword(password);
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getEmailErrorMessage(), "ელ-ფოსტა ელ-ფოსტა ველში არასწორია.", "Email error message is not correct");
    }

    @Test(priority = 12)
    @Description("Negative Case: Check registration with email without provider")
    public void checkRegistrationWithEmailWithoutProvider(){
        String password = faker.internet().password(6, 10, true, true, true);
        steps.enterEmail(emailWithoutProvider);
        steps.enterPassword(password);
        steps.confirmPassword(password);
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getEmailErrorMessage(), "ელ-ფოსტა ელ-ფოსტა ველში არასწორია.", "Email error message is not correct");
    }

    @Test(priority = 13)
    @Description("Negative Case: Check registration with invalid password confirmation")
    public void checkRegistrationWithInvalidPasswordConfirmation(){
        steps.enterEmail(faker.internet().emailAddress());
        steps.enterPassword(faker.internet().password(6, 10, true, true, true));
        steps.confirmPassword(faker.internet().password(6, 10, true, true, false));
        steps.enterFirstname(faker.name().firstName());
        steps.enterLastname(faker.name().lastName());
        steps.submitRegisterBtn();
        Assert.assertEquals(steps.getPasswordErrorMessage(), "პაროლები ველებში 'დაადასტურეთ პაროლი' და 'დაადასტურეთ პაროლი' ერთმანეთს არ ემთხვევა.", "Password error message is not correct");
        Assert.assertEquals(steps.getPassConfirmationErrorMessage(), "პაროლები ველებში 'პაროლი' და 'პაროლი' ერთმანეთს არ ემთხვევა.", "Password error message is not correct");
    }
}
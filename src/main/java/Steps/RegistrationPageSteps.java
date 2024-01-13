package Steps;

import Data.ICommon;
import Pages.RegistrationPageObject;
import Utility.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPageSteps extends RegistrationPageObject implements ICommon {
    BrowserDriver browserDriver;
    public RegistrationPageSteps(WebDriver driver) {
        browserDriver = new BrowserDriver(driver);
        PageFactory.initElements(driver, this);
    }

    @Step
    public WebElement getUserType(){
        return userType;
    }

    @Step
    public WebElement getEmailElement(){
        return emailLabel;
    }

    @Step
    public WebElement getPasswordElement(){
        return passwordField;
    }

    @Step
    public WebElement getPasswordConfirmationElement(){
        return passConfirmationField;
    }

    @Step
    public WebElement getFirstNameElement(){
        return firstnameInput;
    }

    @Step
    public WebElement getLastNameElement(){
        return lastnameInput;
    }

    @Step
    public void enterEmail(String text) {
        emailLabel.sendKeys(text);
    }

    @Step
    public void enterPassword(String text) {
        passwordField.sendKeys(text);
    }

    @Step
    public void confirmPassword(String text) {
        passConfirmationField.sendKeys(text);
    }

    @Step
    public void enterFirstname(String text){
        firstnameInput.sendKeys(text);
    }

    @Step
    public void enterLastname(String text){
        lastnameInput.sendKeys(text);
    }

    @Step
    public void submitRegisterBtn() {
        registrationBtn.click();
    }

    @Step
    public String getEmailErrorMessage(){
        return emailErrorMessage.getText();
    }

    @Step
    public String getPasswordErrorMessage(){
        return passwordErrorMessage.getText();
    }

    @Step
    public String getPassConfirmationErrorMessage(){
        return passConfirmationErrorMessage.getText();
    }
}

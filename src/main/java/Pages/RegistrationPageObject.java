package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageObject {
    @FindBy(id = "user_type")
    protected WebElement userType;

    @FindBy(id = "email")
    protected WebElement emailLabel;

    @FindBy(id = "password1")
    protected WebElement passwordField;

    @FindBy(name = "user_data[password2]")
    protected WebElement passConfirmationField;

    @FindBy(name = "user_data[firstname]")
    protected WebElement firstnameInput;

    @FindBy(id = "elm_7")
    protected WebElement lastnameInput;

    @FindBy(name = "dispatch[profiles.update]")
    protected WebElement registrationBtn;

    @FindBy(id = "email_error_message")
    protected WebElement emailErrorMessage;

    @FindBy(id = "password1_error_message")
    protected WebElement passwordErrorMessage;

    @FindBy(id = "password2_error_message")
    protected WebElement passConfirmationErrorMessage;
}

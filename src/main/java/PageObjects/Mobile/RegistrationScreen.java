package PageObjects.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class RegistrationScreen extends AbstractScreen {

    @AndroidFindBy(xpath = "//*[@text='Username']")
    private MobileElement userNameLabel;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputUsername")
    private MobileElement userNameTextBox;

    @AndroidFindBy(xpath = "//*[@text='E-Mail']")
    private MobileElement emailLabel;

    @AndroidFindBy(accessibility = "email of the customer")
    private MobileElement emailTextBox;

    @AndroidFindBy(xpath = "//*[@text='Password']")
    private MobileElement passwordLabel;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputPassword")
    private MobileElement passwordTextBox;

    @AndroidFindBy(xpath = "//*[@text='Name']")
    private MobileElement nameLabel;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputName")
    private MobileElement nameTextBox;

    @AndroidFindBy(xpath = "//*[@text='Programming Languge']")
    private MobileElement programmingLanguageLabel;

    @AndroidFindBy(id = "android:id/text1")
    private MobileElement programmingLanguageDropdown;

    @AndroidFindBy(xpath = "//*[@text='TandC']")
    private MobileElement tnCLabel;

    @AndroidFindBy(xpath = "//*[@text='I accept adds']")
    private MobileElement acceptAddsCheckbox;

    @AndroidFindBy(xpath = "//*[@text='Register User (verify)']")
    private MobileElement userVerifyButton;

    @AndroidFindBy(id = "label_name_data")
    private MobileElement nameFieldTextLabel;

    @AndroidFindBy(id = "label_username_data")
    private MobileElement userNameFieldTextLabel;

    @AndroidFindBy(id = "label_password_data")
    private MobileElement passwordFieldTextLabel;

    @AndroidFindBy(id = "label_email_data")
    private MobileElement emailFieldTextLabel;

    @AndroidFindBy(id = "label_preferedProgrammingLanguage_data")
    private MobileElement programmingLanguageFieldTextLabel;

    @AndroidFindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    private MobileElement verifyUserButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
    private MobileElement registerUserButton;

    public RegistrationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        switchContext("Native");
    }

    public boolean isUserNameFieldPresent() {
        hideKeyboard();
        return isElementPresent(userNameLabel) && isElementPresent(userNameTextBox);
    }

    public boolean isEmailFieldPresent() {
        return isElementPresent(emailLabel) && isElementPresent(emailTextBox);
    }

    public boolean isPasswordFieldPresent() {
        return isElementPresent(passwordLabel) && isElementPresent(passwordTextBox);
    }

    public boolean isNameFieldPresent() {
        return isElementPresent(nameLabel) && isElementPresent(nameTextBox);
    }

    public boolean isProgrammingLanguageFieldPresent() {
        return isElementPresent(programmingLanguageLabel) && isElementPresent(programmingLanguageDropdown);
    }

    public boolean isTnCFieldPresent() {
        return isElementPresent(tnCLabel) && isElementPresent(acceptAddsCheckbox);
    }

    public boolean isRegisterUserFieldPresent() {
        hideKeyboard();
        return isElementPresent(userVerifyButton);
    }

    public String getDefaultName() {
        return getText(nameTextBox);
    }

    public String getDefaultLanguage() {
        return getText(programmingLanguageDropdown);
    }

    public void enterUsername(String userName) {
        enterText(userNameTextBox, userName);
    }

    public void enterEmailId(String email) {
        enterText(emailTextBox, email);
    }

    public void enterPassword(String password) {
        enterText(passwordTextBox, password);
    }

    public void enterName(String name) {
        clearTextBox(nameTextBox);
        enterText(nameTextBox, name);
    }

    public void selectProgrammingLanguage(String programmingLanguage) {
        clickButton(programmingLanguageDropdown);
        clickButton(By.xpath("//*[@text='" + programmingLanguage + "']"));
    }

    public void clickIAcceptButton() {
        clickButton(acceptAddsCheckbox);
    }

    public void clickVerifyUserButton() {
        clickButton(verifyUserButton);
    }

    public String getUserName() {
        return getText(userNameFieldTextLabel);
    }

    public String getEmail() {
        return getText(emailFieldTextLabel);
    }

    public String getPassword() {
        return getText(passwordFieldTextLabel);
    }

    public String getName() {
        return getText(nameFieldTextLabel);
    }

    public String getProgrammingLanguage() {
        return getText(programmingLanguageFieldTextLabel);
    }

    public void clickOnRegisterUserButton() {
        clickButton(registerUserButton);
    }
}

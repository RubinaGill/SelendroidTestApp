package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains methods representing user interactions on Home Screen
 */
public class HomeScreen extends AbstractScreen {

    public HomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/title")
    private MobileElement titleLabel;

    @AndroidFindBy(xpath = "//*[@text='Hello Default Locale, Selendroid-test-app!']")
    private MobileElement welcomeText;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='l10nCD']/android.widget.TextView")
    private MobileElement localizationText;

    @AndroidFindBy(accessibility = "buttonTestCD")
    private MobileElement enButton;

    @AndroidFindBy(accessibility = "buttonStartWebviewCD")
    private MobileElement chromeIconButton;

    @AndroidFindBy(accessibility = "startUserRegistrationCD")
    private MobileElement startRegistrationButton;

    @AndroidFindBy(accessibility = "my_text_fieldCD")
    private MobileElement textBox;

    @AndroidFindBy(accessibility = "waitingButtonTestCD")
    private MobileElement showProgressButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds_check_box")
    private MobileElement acceptAddsCheckBox;

    @AndroidFindBy(accessibility = "visibleButtonTestCD")
    private MobileElement displayTextViewButton;

    @AndroidFindBy(accessibility = "showToastButtonCD")
    private MobileElement displayToastButton;

    @AndroidFindBy(accessibility = "showPopupWindowButtonCD")
    private MobileElement displayWindowPopupButton;

    @AndroidFindBy(accessibility = "exceptionTestButtonCD")
    private MobileElement unhandledExceptionButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/exceptionTestField")
    private MobileElement throwExceptionTextBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/topLevelElementTest")
    private MobileElement focusOnLayoutButton;

    public boolean isWelcomeTextPresent() {
        return isElementPresent(welcomeText);
    }

    public boolean isLocalizationTextPresent() {
        return isElementPresent(localizationText);
    }

    public boolean isEnButtonPresent() {
        return isElementPresent(enButton);
    }

    public boolean isChromeIconButtonPresent() {
        return isElementPresent(chromeIconButton);
    }

    public boolean isStartRegistrationButtonPresent() {
        return isElementPresent(startRegistrationButton);
    }

    public boolean isTextBoxPresent() {
        return isElementPresent(textBox);
    }

    public boolean isShowProgressButtonPresent() {
        return isElementPresent(showProgressButton);
    }

    public boolean isAcceptAddsCheckBoxPresent() {
        return isElementPresent(acceptAddsCheckBox);
    }

    public boolean isDisplayTextViewButtonPresent() {
        return isElementPresent(displayTextViewButton);
    }

    public boolean isDisplayToastButtonPresent() {
        return isElementPresent(displayToastButton);
    }

    public boolean isDisplayWindowPopupButtonPresent() {
        return isElementPresent(displayWindowPopupButton);
    }

    public boolean isUnhandledExceptionButtonPresent() {
        return isElementPresent(unhandledExceptionButton);
    }

    public boolean isThrowExceptionTextBoxPresent() {
        return isElementPresent(throwExceptionTextBox);
    }

    public boolean isFocusOnLayoutButtonPresent() {
        return isElementPresent(focusOnLayoutButton);
    }

    public String getTitle() {
        return getText(titleLabel);
    }

    public void clickOnEnButton() {
        clickButton(enButton);
    }

    public void endActivity(String action) {
        clickButton(By.xpath("//*[@text='" + action + "']"));
    }

    public void clickOnChromeButton() {
        clickButton(chromeIconButton);
    }

    public void clickRegisterLogoButton() {
        clickButton(startRegistrationButton);
    }
}

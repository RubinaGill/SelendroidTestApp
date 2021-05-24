package PageObjects.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarWebViewScreen extends AbstractScreen {
    @FindBy(id = "name_input")
    private WebElement enterNameTextBox;

    @FindBy(xpath = "//body")
    private WebElement selectedDetailsText;

    @FindBy(xpath = "//select[@name='car']")
    private WebElement preferredCarDropdown;

    @FindBy(xpath = "//input[@value='Send me your name!']")
    private WebElement sendUserNameButton;

    @FindBy(xpath = "//a")
    private WebElement startAgainLink;

    public CarWebViewScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean validateTitle(String expectedTitle) {
        return isElementPresent(By.xpath("//*[@text='" + expectedTitle + "']"));
    }

    public boolean validateText(String expectedText) {
        switchContext("hybrid");
        return isElementPresent(By.xpath("//*[text()='" + expectedText + "']"));
    }

    public boolean enterName(String userName) {
        try {
            clearTextBox(enterNameTextBox);
            enterText(enterNameTextBox, userName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectsPreferredCar(String preferredCar) {
        selectFromDropdown(preferredCarDropdown, preferredCar);
    }

    public void clickSendUserNameButton() {
        clickButton(sendUserNameButton);
    }

    public boolean validateUserNameAndPreferredCar(String expectedUserName, String expectedPreferredCar) {
        String actualText = getText(selectedDetailsText);
        return actualText.contains(expectedUserName) && actualText.contains(expectedUserName);
    }

    public void clickOnStartAgainLink() {
        clickButton(startAgainLink);
    }

    public String getDefaultCarSelected() {
        return getSelectedValue(preferredCarDropdown);
    }
}

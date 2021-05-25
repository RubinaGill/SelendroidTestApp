package PageObjects.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static io.appium.java_client.touch.offset.PointOption.point;

public class AbstractScreen {
    private WebDriverWait wait;
    AppiumDriver driver;

    public AbstractScreen(AppiumDriver driver) {
        this.driver = driver;

        //to initialize WebElements referred by @FindBy annotation
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        //explicit wait
        wait = new WebDriverWait(driver, 60);

    }

    /**
     * Use this method to find if element is visible on screen
     *
     * @param element, WebElement to be inspected
     * @return true if element is present, otherwise false
     */
    boolean isElementPresent(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Use this method to check whether element is in DOM
     *
     * @param elementLocator, used to find element
     * @return true if element is inside DOM
     */
    boolean isElementPresent(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Use this method to simulate typing into an element, which may set its value
     *
     * @param element, WebElement whose value is to be set
     * @param text,    String to send to an element
     */
    void enterText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to click on an element
     *
     * @param element, WebElement to perform click operation
     */
    void clickButton(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method to hide keyboard
     */
    void hideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to perform click operation on element
     *
     * @param locator, used to find an element
     */
    void clickButton(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to get visible innerText of the element
     *
     * @param element, WebElement to process
     * @return innerText of the WebElement
     */
    String getText(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Select all options that have a value equals the argument.
     *
     * @param dropdownElement, dropdown to be selected
     * @param value            The value to match against
     */
    void selectFromDropdown(WebElement dropdownElement, String value) {
        try {
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select all options that have a value equals the argument.
     *
     * @param dropdownElement, dropdown to be selected
     */
    String getSelectedValue(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText();
    }

    /**
     * tap at particular coordinates
     *
     * @param x x-coordinates
     * @param y y-coordinates
     */
    public void pressByCoordinates(int x, int y) {
        TouchAction touchAction=new TouchAction(driver);
        touchAction.longPress(point(x, y)).release().perform();
    }

    /**
     * An expectation for wait till checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element             the WebElement
     * @param timeInSecondsToWait time in seconds to wait
     */
    boolean waitTillElementIsVisible(WebElement element, int timeInSecondsToWait) {
        try {
            for (int counter = 0; counter < timeInSecondsToWait; counter++) {
                try {
                    wait.until(ExpectedConditions.visibilityOf(element));
                    Thread.sleep(1000);
                } catch (Exception e) {
                    return true;
                }
            }
            return !isElementPresent(element);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * If this element is a text entry element, this will clear the value. Has no effect on other
     * elements. Text entry elements are INPUT and TEXTAREA elements.
     *
     * @param element the WebElement
     */
    void clearTextBox(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method to switch context from/to hybrid and native
     *
     * @param context, values can be either Hybrid or Native
     */
    void switchContext(String context) {
        if (StringUtils.containsIgnoreCase(context, "Native")) {
            context = "NATIVE_APP";
        } else if (StringUtils.containsIgnoreCase(context, "Hybrid")) {
            context = "WEBVIEW_io.selendroid.testapp";
        }
        if (StringUtils.containsIgnoreCase(driver.getContext(), context)) return;
        Set<String> contextName = driver.getContextHandles();
        for (String contexts : contextName) {
            if (StringUtils.containsIgnoreCase(contexts, context)) {
                driver.context(contexts);
                break;
            }
        }
    }
}

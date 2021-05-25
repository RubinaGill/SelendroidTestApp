package PageObjects.Web;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    private WebDriverWait wait;
    WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;

        //to initialize WebElements referred by @FindBy annotation
        PageFactory.initElements(driver, this);

        //explicit wait
        wait = new WebDriverWait(driver, 60);

    }

    /**
     * method to navigate to url
     *
     * @param url url to navigate to
     */
    void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to switch frames
     *
     * @param frameElement : web element
     */
    protected void switchToFrame(WebElement frameElement) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to click using control key
     *
     * @param locator locator for element to click on
     */
    protected void clickWithControlKey(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            new Actions(driver).keyDown(Keys.CONTROL).click(element).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to scroll element to view
     *
     * @param locator locator of element
     */
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to get attribute of element
     *
     * @param locator   locator of element
     * @param attribute attribute to get
     * @return String value of attribute
     */
    protected String getAttributeOfElement(By locator, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attribute);
    }

    /**
     * method to drag one element to target element
     * @param source element to drag
     * @param target target element
     */
    protected void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).build().perform();
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
}

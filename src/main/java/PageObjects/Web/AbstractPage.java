package PageObjects.Web;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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
     * @param url
     */
    void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean switchToFrame(WebElement frameElement) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean clickWithControlKey(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            new Actions(driver).keyDown(Keys.CONTROL).click(element).build().perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void scrollToElement(By locator){
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String getAttributeOfElement(By locator, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attribute);
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).build().perform();
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

    WebElement getElement(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
     * Use this method to get visible innerText of the element
     *
     * @param elementLocator, used to find an Element
     * @return innerText of the WebElement
     */
    String getText(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Use this method to get text of all elements of a list
     *
     * @param listOfSearch, used as parent element. For example table with locator By.xpath("//table")
     * @param childLocator, used to find child element. For example td By.xpath("./tr/td")
     * @return text of visible subElements
     */
    List<String> getAllStringItemsOfList(WebElement listOfSearch, By childLocator) {

        try {
            List<WebElement> elementList;
            List<String> nameList = new ArrayList<>();
            elementList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(listOfSearch, childLocator));

            //process every element in a list, add its innerText to nameList
            for (WebElement elementName : elementList) {
                nameList.add(elementName.getText());
            }
            return nameList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Use this method to get all the WebElements in a list
     *
     * @param listOfSearch, used as parent element. For example table with locator By.xpath("//table")
     * @param childLocator, used to find child element. For example td By.xpath("./tr/td")
     * @return subElements of a list
     */
    List<WebElement> getAllItemsOfList(WebElement listOfSearch, By childLocator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(listOfSearch, childLocator));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select all options that have a value matching the argument.
     *
     * @param dropdownElement, dropdown to be selected
     * @param value            The value to match against
     */
    void selectValueFromDropdown(WebElement dropdownElement, String value) {
        try {
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(dropdownElement, By.xpath("//option[contains(text(), '" + value + "')]"))).click();
        } catch (Exception e) {
            e.printStackTrace();
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


    void waitForDuration(int timeInMilliSeconds) {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     */
    void waitUntilVisibilityOfElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
}

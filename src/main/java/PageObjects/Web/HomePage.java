package PageObjects.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@id='content']/iframe")
    private WebElement demoFrame;

    @FindBy(id = "draggable")
    private WebElement dragSource;

    @FindBy(id = "droppable")
    private WebElement dropTarget;

    @FindBy(xpath = "//*[@id='droppable']/p")
    private WebElement droppedText;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToWebsite(String url) {
        navigateToUrl(url);
    }

    public void selectOption(String component) {
        clickButton(By.xpath("//*[@id='sidebar']//*[text()='" + component + "']"));
        switchToFrame(demoFrame);
    }

    public void dragAndDropComponent() {
        dragAndDrop(dragSource, dropTarget);
    }

    public boolean isDragAndDropSuccessful() {
        return getText(droppedText).equals("Dropped!");
    }

    public void selectOptionsFromSelectable(String option1, String option2, String option3) {
        scrollToElement(By.xpath("//ol[@id='selectable']/li[text()='" + option1 + "']"));
        clickWithControlKey(By.xpath("//ol[@id='selectable']/li[text()='" + option1 + "']"));
        clickWithControlKey(By.xpath("//ol[@id='selectable']/li[text()='" + option2 + "']"));
        clickWithControlKey(By.xpath("//ol[@id='selectable']/li[text()='" + option3 + "']"));
    }

    public boolean areOptionsSelected(String option1, String option2, String option3) {
        return getAttributeOfElement(By.xpath("//ol[@id='selectable']/li[text()='" + option1 + "']"), "class").contains("ui-selected") &&
                getAttributeOfElement(By.xpath("//ol[@id='selectable']/li[text()='" + option2 + "']"), "class").contains("ui-selected") &&
                getAttributeOfElement(By.xpath("//ol[@id='selectable']/li[text()='" + option3 + "']"), "class").contains("ui-selected");
    }

    public void selectOptionFromHorizontalToolBar(String option) {
        clickButton(By.xpath("(//*[legend='Rental Car'])[1]//label[text()='"+option+"']"));
    }

    public void selectOptionFromVerticalToolBar(String option) {
        clickButton(By.xpath("(//*[legend='Rental Car'])[2]//label[text()='"+option+"']"));
    }

    public boolean areHorizontalOptionsSelected(String option1, String option2) {
        return getAttributeOfElement(By.xpath("(//*[legend='Rental Car'])[1]//label[text()='"+option1+"']"),"class").contains("active")&&
         getAttributeOfElement(By.xpath("(//*[legend='Rental Car'])[1]//label[text()='"+option2+"']"),"class").contains("active");
    }

    public boolean areVerticalOptionsSelected(String option1, String option2) {
        return getAttributeOfElement(By.xpath("(//*[legend='Rental Car'])[2]//label[text()='"+option1+"']"),"class").contains("active")&&
                getAttributeOfElement(By.xpath("(//*[legend='Rental Car'])[2]//label[text()='"+option2+"']"),"class").contains("active");
    }
}

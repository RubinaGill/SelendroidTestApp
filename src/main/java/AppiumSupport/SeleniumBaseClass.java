package AppiumSupport;

import org.openqa.selenium.WebDriver;

public class SeleniumBaseClass<SELF, DRIVER extends WebDriver> {

    public static ChromeDriverBuilder forChrome() {
        return new ChromeDriverBuilder();
    }
}

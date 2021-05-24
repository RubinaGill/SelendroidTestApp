package AppiumSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeDriverBuilder {
    WebDriver webdriver = null;

    public ChromeDriver build(String chromePath) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromePath);
        System.setProperty("webdriver.chrome.port", "8080");

        return new ChromeDriver(chromeOptions);
    }
}

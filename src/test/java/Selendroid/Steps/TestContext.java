package Selendroid.Steps;

import AppiumSupport.AppiumBaseClass;
import AppiumSupport.AppiumLauncher;
import AppiumSupport.SeleniumBaseClass;
import Config.Config;
import Logger.Log;
import PageObjects.Mobile.ScreenObjectManager;
import PageObjects.Web.PageObjectManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import java.net.MalformedURLException;
import java.util.Properties;

public class TestContext {
    public static Properties config;
    public static ScreenObjectManager screenObjectManager;
    public static PageObjectManager pageObjectManager;
    protected AppiumDriver driver;
    protected WebDriver chromeDriver;
    AppiumDriverLocalService server;

    @Before(order = 0)
    public void readConfig(Scenario scenario) {
        Log.info("Read configuration properties");
        config = Config.getAllProperties();

        Log.startTestCase(scenario.getName());
    }

    @Before(value = "@Mobile", order = 1)
    public void appLaunch() {

        Log.info("Launch Appium");
        if (server==null) {
            server = AppiumLauncher.startServer(config.getProperty("NODE_PATH"), config.getProperty("APPIUM_JS_PATH"),config.getProperty("DEVICE_NAME"));
            server.start();
        }

        Log.info("Launch Application");
        try {
            this.driver = AppiumBaseClass.forAndroid().build(server, config.getProperty("APP_PATH"), config.getProperty("APP_NAME"), config.getProperty("PLATFORM_VERSION"), config.getProperty("DEVICE_NAME"), config.getProperty("APP_PACKAGE"), config.getProperty("APP_ACTIVITY"));
        } catch (MalformedURLException e) {
            Log.error("Appium URL is not valid");
        }
        screenObjectManager = new ScreenObjectManager(driver);
    }

    @Before(value = "@Web", order = 1)
    public void createDriver() {

        Log.info("Launch Browser");
        this.chromeDriver = SeleniumBaseClass.forChrome().build(config.getProperty("CHROME_EXECUTABLE_PATH"));
        chromeDriver.manage().window().maximize();

        pageObjectManager = new PageObjectManager(chromeDriver);
    }

    @After(value = "@IB", order = 1)
    public void appClose() {
        if (driver != null) {
            driver.quit();
        }
    }

    @After(value = "@Web", order = 1)
    public void quitDriver() {
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
    }

    @After(order = 0)
    public void logStatus(Scenario scenario) {
        Log.endTestCase(scenario.getName(), scenario.getStatus().name());
    }

    @AfterSuite
    public void closeAppiumServer(){
        if(server!=null){
            server.stop();
        }
    }
}

package Selendroid.Runner;


import AppiumSupport.AppiumBaseClass;
import AppiumSupport.SeleniumBaseClass;
import Config.Config;
import Logger.Log;
import PageObjects.Mobile.ScreenObjectManager;
import PageObjects.Web.PageObjectManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.testng.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Properties;

@CucumberOptions(
        features = "src/test/java/Selendroid/Features",
        glue = "Selendroid/Steps",
        tags = "@API",
        plugin = {
                "pretty"
        }
)
public class CucumberRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    public static Properties config;
    public static ScreenObjectManager screenObjectManager;
    public static PageObjectManager pageObjectManager;
    protected AppiumDriver driver;
    protected WebDriver chromeDriver;

    @BeforeMethod()
    public void appLaunch(ITestContext testContext) {
        Log.info("Read configuration properties");
        config = Config.getAllProperties();
        if (testContext.getCurrentXmlTest().getName().contains("Mobile")) {

//        Log.info("Launch Appium Server");
//        AppiumLauncher.runProcess(false, "appium -a 0.0.0.0 -p 4723");

            Log.info("Launch Application");
            try {
                this.driver = AppiumBaseClass.forAndroid().build(config.getProperty("APP_PATH"), config.getProperty("APP_NAME"), config.getProperty("PLATFORM_VERSION"), config.getProperty("DEVICE_NAME"), config.getProperty("APP_PACKAGE"), config.getProperty("APP_ACTIVITY"));
            } catch (MalformedURLException e) {
                Log.error("Appium URL is not valid");
            }

            screenObjectManager = new ScreenObjectManager(driver);
        }
        if (testContext.getCurrentXmlTest().getName().contains("Web")) {

            Log.info("Launch Url");

            this.chromeDriver = SeleniumBaseClass.forChrome().build(config.getProperty("CHROME_EXECUTABLE_PATH"));
            chromeDriver.manage().window().maximize();

            pageObjectManager = new PageObjectManager(chromeDriver);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

//    @BeforeMethod
//    public void updateName() {
//        Log.startTestCase();
//    }

    @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

    @AfterMethod
    public void appClose() {
        if(driver!=null) {
            driver.quit();
        }
        if(chromeDriver!=null) {
            chromeDriver.quit();
        }
        //    Log.endTestCase(scenario.getName(), scenario.getStatus().name());
    }
}
    
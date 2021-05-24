package Selendroid.Runner;


import AppiumSupport.AppiumLauncher;
import AppiumSupport.AppiumSupport;
import AppiumSupport.AppiumBaseClass;
import Config.Config;
import Logger.Log;
import PageObjects.PageObjectManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.testng.*;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Properties;

@CucumberOptions(
        features = "src/test/java/Selendroid/Feature",
        glue = "Selendroid/Steps",
        tags = "@UserRegistration",
        plugin = {
                "pretty",
                "json:target/cucumber_reports/Cucumber.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)
public class CucumberRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    public static Properties config;
    public static PageObjectManager pageObjectManager;
    private AppiumDriver driver;

    @BeforeSuite()
    public void appLaunch() {
        Log.info("Read configuration properties");
        config = Config.getAllProperties();

//        Log.info("Launch Appium Server");
//        AppiumLauncher.runProcess(false, "appium -a 0.0.0.0 -p 4723");

        Log.info("Launch Application");
        try {
            this.driver = AppiumBaseClass.forAndroid().build(config.getProperty("APP_PATH"), config.getProperty("APP_NAME"), config.getProperty("PLATFORM_VERSION"), config.getProperty("DEVICE_NAME"), config.getProperty("APP_PACKAGE"), config.getProperty("APP_ACTIVITY"));
        } catch (MalformedURLException e) {
            Log.error("Appium URL is not valid");
        }

        pageObjectManager = new PageObjectManager(driver);
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

    @AfterSuite
    public void appClose() {
        AppiumSupport.instance.appClose();
        //     AppiumSupport.instance.stopAppiumServer();
        //    Log.endTestCase(scenario.getName(), scenario.getStatus().name());
    }
}
    
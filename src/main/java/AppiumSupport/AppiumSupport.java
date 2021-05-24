package AppiumSupport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumSupport {
    private static AppiumDriverLocalService server;

    public static final AppiumSupport instance = new AppiumSupport();
    public AppiumDriver driver;

    public AppiumSupport appLaunch(String appUrl, String appName, String appiumIp, String appiumPort, String platformVersion, String deviceName, String appPackage, String appActivity) throws MalformedURLException {

        File appDir = new File(System.getProperty("user.dir") + appUrl);
        File app = new File(appDir, appName);

        String url = appiumIp + ":" + appiumPort + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);

        return instance;
    }

    public void appClose() {
        try {
            if (driver != null) {
                driver.closeApp();
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver = null;
        }
    }

    public void startServer(String nodePath, String appiumJsPath, String appiumIp, String appiumPort) {
//        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .withAppiumJS(new File(nodePath))
//                .usingPort(4723).withIPAddress("127.0.0.1"));
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
//        serviceBuilder.usingDriverExecutable(new File(nodePath));
//        serviceBuilder.withAppiumJS(new File(appiumJsPath));
//        serviceBuilder.usingPort(Integer.parseInt(appiumPort));
        serviceBuilder.withIPAddress("0.0.0.0");
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
    }

    public void stopAppiumServer() {
        server.stop();
    }

}

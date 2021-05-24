package AppiumSupport;


import io.appium.java_client.AppiumDriver;

public abstract class AppiumThreadLocal {
    public static AppiumDriver createAppiumDriver() {
        return AppiumSupport.instance.driver;
    }

    private static final ThreadLocal<AppiumSupport> tlDriver = new ThreadLocal<>();

    public static synchronized void setDriver(AppiumSupport driver) {
        tlDriver.set(driver);
    }

    public static synchronized AppiumSupport getDriver() {
        return tlDriver.get();
    }
}


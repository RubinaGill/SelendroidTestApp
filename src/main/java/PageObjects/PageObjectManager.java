package PageObjects;

import io.appium.java_client.AppiumDriver;

public class PageObjectManager {
    private final AppiumDriver driver;

    private HomeScreen homeScreen;
    private CarWebViewScreen carWebViewScreen;
    private RegistrationScreen registrationScreen;

    public PageObjectManager(AppiumDriver driver) {
        this.driver = driver;
    }

    public HomeScreen getHomeScreenPage() {
        return (homeScreen == null) ? homeScreen = new HomeScreen(driver) : homeScreen;
    }

    public CarWebViewScreen getCarWebViewScreen() {
        return (carWebViewScreen == null) ? carWebViewScreen = new CarWebViewScreen(driver) : carWebViewScreen;
    }

    public RegistrationScreen getRegistrationScreen() {
        return (registrationScreen == null) ? registrationScreen = new RegistrationScreen(driver) : registrationScreen;
    }

}

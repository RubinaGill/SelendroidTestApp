package Selendroid.Steps;

import PageObjects.HomeScreen;
import Selendroid.Runner.CucumberRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeScreenSteps extends CucumberRunner {
    HomeScreen homeScreen = pageObjectManager.getHomeScreenPage();

    @Given("user launches the app and is on home screen")
    public void user_launches_the_app_and_is_on_home_screen() {
        Assert.assertTrue(homeScreen.isWelcomeTextPresent(), "user is not navigated to Home Screen on trying to launch app");
    }

    @Then("user verifies the title as {string} on home screen")
    public void user_verifies_the_title_on_home_screen(String expectedTitle) {
        Assert.assertEquals(homeScreen.getTitle(), expectedTitle, "user is not able to view expected Title");
    }

    @Then("user verifies UI elements")
    public void user_verifies_UI_elements() {
        Assert.assertTrue(homeScreen.isLocalizationTextPresent(), "user is not able to view Localization Text");
        Assert.assertTrue(homeScreen.isEnButtonPresent(), "user is not able to view button for EN language");
        Assert.assertTrue(homeScreen.isChromeIconButtonPresent(), "user is not able to view button for chrome browser");
        Assert.assertTrue(homeScreen.isStartRegistrationButtonPresent(), "user is not able to button to start registration");
        Assert.assertTrue(homeScreen.isTextBoxPresent(), "user is not able to view TextBox");
        Assert.assertTrue(homeScreen.isShowProgressButtonPresent(), "user is not able to view button for show progress bar");
        Assert.assertTrue(homeScreen.isDisplayTextViewButtonPresent(), "user is not able to view button for display Text view");
        Assert.assertTrue(homeScreen.isDisplayToastButtonPresent(), "user is not able to view button for toast view");
        Assert.assertTrue(homeScreen.isDisplayWindowPopupButtonPresent(), "user is not able to view button to display pop-up window");
        Assert.assertTrue(homeScreen.isThrowExceptionTextBoxPresent(), "user is not able to view button to throw exception");
        Assert.assertTrue(homeScreen.isThrowExceptionTextBoxPresent(), "user is not able to view textBox to enter exception to throw");
        Assert.assertTrue(homeScreen.isFocusOnLayoutButtonPresent(), "user is not able to view button to focus layout");
    }

    @When("user taps on EN button")
    public void user_taps_on_en_button() {
        homeScreen.clickOnEnButton();
    }

    @When("user selects option as {string} to end activity")
    public void user_selects_option_as_to_end_activity(String ifEndActivity) {
        homeScreen.endActivity(ifEndActivity);
    }

    @Then("user must be navigated back to home page")
    public void user_must_be_navigated_back_to_home_page() {
        Assert.assertTrue(homeScreen.isWelcomeTextPresent(), "user is not navigated back to Home Screen");
    }

    @When("user taps on Chrome logo button")
    public void user_taps_on_chrome_logo_button() {
        homeScreen.clickOnChromeButton();
    }

    @When("user taps on File logo button")
    public void user_taps_on_file_logo_button() {
        homeScreen.clickRegisterLogoButton();
    }
}

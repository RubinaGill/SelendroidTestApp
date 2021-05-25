package Selendroid.Steps.Mobile;

import PageObjects.Mobile.HomeScreen;
import Selendroid.Steps.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeScreenSteps {
    HomeScreen homeScreen = TestContext.screenObjectManager.getHomeScreenPage();

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

    @When("user taps on Show Progress bar")
    public void user_taps_on_show_progress_bar() {
        homeScreen.showProgressBar();
    }

    @Then("user waits for progress bar to disappear")
    public void user_waits_for_progress_bar_to_disappear() {
        Assert.assertTrue(homeScreen.waitUntilProgressBarIsVisible(), "user is unable to wait for progress bar to complete");
    }

    @When("user taps on Displays a toast button")
    public void user_taps_on_displays_a_toast_button() {
        homeScreen.clickOnToastButton();
    }

    @Then("user verifies toast {string} on home screen")
    public void user_verifies_toast_on_home_screen(String message) {
        Assert.assertEquals(homeScreen.getToastMessage(), message, "user is unable to view toast message");
    }

    @When("user taps on Display Pop up Window button")
    public void user_taps_on_display_pop_up_window_button() {
        homeScreen.clickOnPopUpWindow();
    }

    @Then("user is able to dismiss the popup")
    public void user_is_able_to_dismiss_the_popup() {
        homeScreen.dismissPopUpWindow();
    }

    @When("user taps on Press to throw unhandled exception button")
    public void user_taps_on_press_to_throw_unhandled_exception_button() {
     homeScreen.clickThrowExceptionButton();
    }

    @Then("user app is stopped")
    public void user_app_is_stopped() {
       Assert.assertTrue(homeScreen.isAppStopAlertPresent(),"user is still on home page");
       Assert.assertFalse(homeScreen.isEnButtonPresent(),"user is not on home screen");
    }

    @When("user types {string} to throw unhandled exception button")
    public void user_types_to_throw_unhandled_exception_button(String exceptionText) {
        for (int i=0; i < exceptionText.length(  ); i++) {
            homeScreen.enterException(""+exceptionText.charAt(i));
            if(i==0){
                Assert.assertTrue(homeScreen.isAppStopAlertPresent(),"user is still on home page");
            }else{
                Assert.assertTrue(homeScreen.isAppStoppingAlertPresent(),"user is still on home page");
            }
            homeScreen.openAppAgain();
        }
    }
}

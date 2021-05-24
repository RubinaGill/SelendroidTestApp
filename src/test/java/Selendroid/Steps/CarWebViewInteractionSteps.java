package Selendroid.Steps;

import PageObjects.CarWebViewScreen;
import Selendroid.Runner.CucumberRunner;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CarWebViewInteractionSteps extends CucumberRunner {
    CarWebViewScreen carWebViewScreen = pageObjectManager.getCarWebViewScreen();

    @Then("user verifies the title as {string}")
    public void user_verifies_the_title_as(String expectedTitle) {
        Assert.assertTrue(carWebViewScreen.validateTitle(expectedTitle), "user is not able to view expected Title");
    }

    @Then("user verifies the text as {string}")
    public void user_verifies_the_text(String expectedText) {
        Assert.assertTrue(carWebViewScreen.validateText(expectedText), "user is not able to view expected Text");
    }

    @Then("user enters name as {string}")
    public void user_enters_name_as(String userName) {
        Assert.assertTrue(carWebViewScreen.enterName(userName), "user is not able to enter username");
    }

    @Then("user selects preferred car as {string}")
    public void user_selects_preferred_car_as(String preferredCar) {
        carWebViewScreen.selectsPreferredCar(preferredCar);
    }

    @Then("user clicks on send me your name button")
    public void user_clicks_on_send_me_your_name_button() {
        carWebViewScreen.clickSendUserNameButton();
    }

    @Then("verifies the confirmation text {string}")
    public void verifies_the_confirmation_text(String confirmationText) {
        Assert.assertTrue(carWebViewScreen.validateText(confirmationText), "user is not able to view confirmation text");
    }

    @Then("user verifies {string} and {string} details")
    public void user_verifies_and_details(String expectedUserName, String expectedPreferredCar) {
        Assert.assertTrue(carWebViewScreen.validateUserNameAndPreferredCar(expectedUserName,expectedPreferredCar), "user is not able to view entered username and selected car");
    }

    @Then("user clicks on bottom link to start again")
    public void user_clicks_on_bottom_link_to_start_again() {
        carWebViewScreen.clickOnStartAgainLink();
    }

    @Then("user verifies default car selected is {string}")
    public void user_verifies_default_car_selected_is(String expectedSelectedCar) {
        Assert.assertEquals(carWebViewScreen.getDefaultCarSelected(),expectedSelectedCar,expectedSelectedCar+"is not selected as default car");
    }
}

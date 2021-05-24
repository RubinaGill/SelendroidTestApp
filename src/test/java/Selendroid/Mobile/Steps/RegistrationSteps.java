package Selendroid.Mobile.Steps;

import PageObjects.Mobile.RegistrationScreen;
import Selendroid.Runner.CucumberRunner;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class RegistrationSteps extends CucumberRunner {
    RegistrationScreen registrationScreen = screenObjectManager.getRegistrationScreen();
   // RegistrationScreen registrationScreen = new RegistrationScreen(driver);

    @Then("user verifies the UI elements of register screen")
    public void user_verifies_the_ui_elements_of_register_screen() {
        Assert.assertTrue(registrationScreen.isUserNameFieldPresent(), "user is not able to view username field");
        Assert.assertTrue(registrationScreen.isEmailFieldPresent(), "user is not able to view email field");
        Assert.assertTrue(registrationScreen.isPasswordFieldPresent(), "user is not able to view password field");
        Assert.assertTrue(registrationScreen.isNameFieldPresent(), "user is not able to view name field");
        Assert.assertTrue(registrationScreen.isProgrammingLanguageFieldPresent(), "user is not able to view programming field");
        Assert.assertTrue(registrationScreen.isTnCFieldPresent(), "user is not able to view accept adds field");
        Assert.assertTrue(registrationScreen.isRegisterUserFieldPresent(), "user is not able to view register user field");
    }

    @Then("user verifies the name field is pre-populated with {string}")
    public void user_verifies_the_name_field_is_pre_populated_with(String defaultName) {
        Assert.assertEquals(registrationScreen.getDefaultName(), defaultName, defaultName + " is not selected in Name Field");
    }

    @Then("user verifies default language is selected as {string}")
    public void user_verifies_default_language_is_selected_as(String defaultLanguage) {
        Assert.assertEquals(registrationScreen.getDefaultLanguage(), defaultLanguage, defaultLanguage + " is not selected in Programming Language Field");
    }

    @Then("user enters the details - {string}, {string}, {string}, {string}, {string}")
    public void user_enters_the_details_as(String userName, String email, String password, String name, String programmingLanguage) {
        registrationScreen.enterUsername(userName);
        registrationScreen.enterEmailId(email);
        registrationScreen.enterPassword(password);
        registrationScreen.enterName(name);
        registrationScreen.selectProgrammingLanguage(programmingLanguage);
    }

    @Then("user accepts adds")
    public void user_accepts_adds() {
        registrationScreen.clickIAcceptButton();
    }

    @Then("user taps on Register User \\(verify)")
    public void user_taps_on_register_user_verify() {
        registrationScreen.clickVerifyUserButton();
    }

    @Then("user verifies details - {string}, {string}, {string}, {string}, {string} on the next screen")
    public void user_verifies_details_on_the_next_screen(String userName, String email, String password, String name, String programmingLanguage) {
        Assert.assertEquals(registrationScreen.getUserName(), userName, "username is not as entered");
        Assert.assertEquals(registrationScreen.getEmail(), email, "email is not as entered");
        Assert.assertEquals(registrationScreen.getPassword(), password, "password is not as entered");
        Assert.assertEquals(registrationScreen.getName(), name, "name is not as entered");
        Assert.assertEquals(registrationScreen.getProgrammingLanguage(), programmingLanguage, "programming language is not as entered");
    }

    @Then("user tap on Register User")
    public void user_tap_on_register_user() {
        registrationScreen.clickOnRegisterUserButton();
    }

}

package Selendroid.Web.Steps;

import PageObjects.Web.HomePage;
import Selendroid.Runner.CucumberRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomePageSteps extends CucumberRunner {
    HomePage homePage = pageObjectManager.getHomePage();

    @Given("user navigates to website")
    public void user_navigates_to_website() {
        homePage.navigateToWebsite(config.getProperty("URL"));
    }

    @When("user selects option as {string}")
    public void user_selects_option_as(String component) {
        homePage.selectOption(component);
    }

    @Then("user is able to drag Drag me to my target component to Drop here component")
    public void user_is_able_to_drag_component_to_component() {
        homePage.dragAndDropComponent();
        Assert.assertTrue(homePage.isDragAndDropSuccessful(), "user is unable to successfully drag and drop");
    }

    @Then("user is able to select items {string},{string},{string}")
    public void user_is_able_to_select_items(String option1, String option2, String option3) {
        homePage.selectOptionsFromSelectable(option1, option2, option3);
        Assert.assertTrue(homePage.areOptionsSelected(option1, option2, option3), "user is unable to select options as group");
    }

    @Then("user is able to select rental car options as {string} and {string} on horizontal toolbar")
    public void user_is_able_to_select_rental_car_options_as_and_on_horizontal_toolbar(String option1, String option2) {
       homePage.selectOptionFromHorizontalToolBar(option1);
       homePage.selectOptionFromHorizontalToolBar(option2);
       Assert.assertTrue(homePage.areHorizontalOptionsSelected(option1,option2),"user is unable to select horizontal options");
    }

    @Then("user is able to select rental car options as {string} and {string} on vertical toolbar")
    public void user_is_able_to_select_rental_car_options_as_and_on_vertical_toolbar(String option1, String option2) {
        homePage.selectOptionFromVerticalToolBar(option1);
        homePage.selectOptionFromVerticalToolBar(option2);
        Assert.assertTrue(homePage.areVerticalOptionsSelected(option1,option2),"user is unable to select horizontal options");
    }
}

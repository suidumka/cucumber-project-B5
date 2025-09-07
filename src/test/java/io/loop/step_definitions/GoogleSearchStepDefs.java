package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.GoogleSearchPage;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GoogleSearchStepDefs {
    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Given("user is on Google search page")
    public void user_is_on_google_search_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("google.url"));
    }

    @When("user types Loop Academy in the google search box and clicks enter")
    public void user_types_loop_academy_in_the_google_search_box_and_clicks_enter() {
        googleSearchPage.searchBox.sendKeys("Loop Academy" + Keys.ENTER);
    }

    @Then("user should be able to see Loop Academy - Google search in the google title")
    public void user_should_be_able_to_see_loop_academy_google_search_in_the_google_title() throws InterruptedException {
        Thread.sleep(3000);
        String actual = Driver.getDriver().getTitle();

        assertEquals("No Match",  "Loop Academy - Google Search", actual);
    }

    @When("user types {string} in the google search box and clicks enter")
    public void user_types_in_the_google_search_box_and_clicks_enter(String input) {
        googleSearchPage.searchBox.sendKeys(input + Keys.ENTER);
    }


    @Then("user should be able to see {string} in the google title")
    public void user_should_be_able_to_see_search_in_the_google_title(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait( Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        String actual = Driver.getDriver().getTitle();
        assertEquals("Expected does NOT match actual", expectedTitle, actual);
    }

    @When("user searches for {string}")
    public void user_searches_for(String country) {
        googleSearchPage.searchBox.sendKeys("What is the capital of " + country + "?" + Keys.ENTER);

    }

    @Then("user should be able to see {string} in the results as capital")
    public void user_should_be_able_to_see_in_the_results_as_capital(String capital) {
        assertEquals("Expected capital city: " + capital + "does NOT match with actual: " + googleSearchPage.capitalName.getText(), capital, googleSearchPage.capitalName.getText() );
    }

    @Then("we love Loop Academy")
    public void we_love_loop_academy() {
        System.out.println("We love Loop Academy");
    }

    @Then("user searches the following items")
    public void user_searches_the_following_items(List<String> items) {
        for (String item : items) {
            googleSearchPage.searchBox.clear();
            googleSearchPage.searchBox.sendKeys(item + Keys.ENTER);
            WebDriverWait wait = new WebDriverWait( Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs(item + " - Google Search"));
            assertEquals("Expected does NOT match actual", item, googleSearchPage.searchBox.getText());
            BrowsersUtils.takeScreenShot();
        }

    }


}

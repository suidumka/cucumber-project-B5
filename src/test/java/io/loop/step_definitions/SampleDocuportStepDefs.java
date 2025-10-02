package io.loop.step_definitions;

import io.cucumber.java.en.*;
import io.loop.pages.POM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SampleDocuportStepDefs {

    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {
        switch (page.trim().toLowerCase()) {
            case "login" -> {
                pages.getLoginPage().insertField(field, input);
                LOG.info(input + " was successfully inserted");
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such field " + page);
        }
    }


    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {
        switch (page.trim().toLowerCase()) {
            case "login" , "choose account" -> { pages.getLoginPage().clickButton(button);
            LOG.info(button + " was successfully clicked");
            Thread.sleep(1000);
            }
            case "left navigate" -> pages.getLeftNavigatePage().clickButton(button);
            case "received doc" -> pages.getReceivedDocsPage().clickButton(button);
            default -> throw new IllegalArgumentException("No such button " + page);
        }
    }

}




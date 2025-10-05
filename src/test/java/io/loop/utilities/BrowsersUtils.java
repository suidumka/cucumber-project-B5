package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.Assert.assertTrue;


public class BrowsersUtils {

    public static Scenario myScenario;

    /**
     * takes screenshot
     *
     * @author SJ
     */

    public static void takeScreenShot() {
        try {
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        } catch (WebDriverException | ClassCastException wbd) {
            wbd.getMessage();
        }
    }


    /**
     * validate if the driver switched to the expected window url or title
     *
     * @param driver
     * @param expectedUrl
     * @param expectedTitle
     * @author SJ
     * implements assertion
     */

    public static void switchWindowAndValidate(WebDriver driver, String expectedUrl, String expectedTitle) {
        // to lowercase the params in order to avoid mistype
        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        // get all window handles, switch one by one and each time check if the url matches expected to stop
        // var windowHandles = driver.getWindowHandles();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedUrl)) {
                break;
            }
        }
        // after stopping on expected url, validate the title
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * @param driver
     * @param targetTitle
     * @author NSH
     */

    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }

    /**
     *
     * clicks any link from Loop practice
     *
     * @param nameOfPage
     * @author SJ
     *
     */
    public static void loopLinkClickMethod(String nameOfPage) {

        WebElement element = Driver.getDriver().findElement(By.xpath("//a[text()='" + nameOfPage + "']"));

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    /**
     * waits for the provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return element
     * @author SJ
     */

    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickable2(WebElement element, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException se){
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * waits for the provided element to be invisible on the page
     *
     * @param element
     * @param timeout return element
     * @author SJ
     */

    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeout return element
     * @author SJ
     */

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * Moves the mouse to given element
     * @param element to hover over
     * @author SJ
     */

    public static void hoverOver(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Scrolls down to an element using JavaScript
     * @param element
     * @author SJ
     */

    public static void scrollElement(WebElement element){

        ((JavascriptExecutor)Driver.getDriver()).executeAsyncScript("augument[0].scrollIntoView(true)", element);
    }
    /**
     * Clicks on element using javaScript
     * @param element
     * @author SJ
     */
    public static void clickWithJS(WebElement element){
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
           // System.out.println("clicked tom"); -- i put this print in try and catch to see which block handled my issue
        } catch (StaleElementReferenceException se) {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
          //  System.out.println("clicked tom222");  -- i put this print in try and catch to see which block handled my issue
        }
    }

    /**
     * Perform double click action
     * @param element
     * @author SJ
     */

    public static void doubleClick(WebElement element){
        new Actions(Driver.getDriver()).doubleClick().perform();
    }

    /**
     * Performs a pause
     * @param milliSeconds
     * @author SJ
     */
    public static void justWait(int milliSeconds) {
        try{
            Thread.sleep(milliSeconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Get elements text
     * @param elements
     * @author SJ
     */

    public static List<String> getElementsText(List<WebElement> elements) {
        List<String> elementsText = new ArrayList<>();
        for (WebElement eachElem : elements) {
            elementsText.add(eachElem.getText());
        }
        return elementsText;
    }


    /**
     * Get elements text with Stream
     * @param elements
     * @author SJ
     */

    public static List<String> getElementsTextWithStream(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement ::getText)
                .collect(Collectors.toList());
    }



}



















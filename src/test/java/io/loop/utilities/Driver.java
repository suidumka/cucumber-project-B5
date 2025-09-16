package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    /*
    Creating a private constructor, so we are closing access to the object of this class from outside the class
     */

    private Driver(){}

    /*
    Making our driver instance private, so it is not reachable from outside the class
    We make it static because we want it to run before everything else, and we will use it in a static method
     */

    private static WebDriver driver;


    /*
    Creating a reusable method that will return the same driver instance every time when we call it
     */

    /**
     * Singleton pattern
     * @return
     */

    public static WebDriver getDriver() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        String browserType = ConfigurationReader.getProperties("browser");
        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
                "--disable-features=PasswordLeakDetection,PasswordManagerOnboarding"
        );
        options.addArguments("--disable-features=HttpsFirstMode,HttpsFirstModeV2");
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> driver = new FirefoxDriver();
                case "safari" -> driver = new SafariDriver();
            }
            assert driver != null;
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // we assign it back to null so that next time we call getDriver(), a new instance will be created
        }
    }
}

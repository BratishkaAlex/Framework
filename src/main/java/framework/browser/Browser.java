package framework.browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public class Browser {

    private static WebDriver driver = null;

    private Browser() {
    }

    public static void setUp(String browser, MutableCapabilities options) {
        if (driver == null) {
            driver = BrowserFactory.getDriver(browser, options);
        }
    }

    public static void maximize() {
        driver.manage().window().maximize();
    }

    public static void enterUrl(String url) {
        driver.get(url);
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

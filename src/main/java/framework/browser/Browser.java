package framework.browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import static framework.utils.LoggerUtil.LOGGER;

public class Browser {

    private static WebDriver driver = null;

    private Browser() {
    }

    public static void setUp(String browser, MutableCapabilities options) {
        if (driver == null) {
            driver = BrowserFactory.getDriver(browser, options);
        }
        LOGGER.warning("Instance of webDriver already exists");
    }

    public static void maximize() {
        LOGGER.warning("Maximize window");
        driver.manage().window().maximize();
    }

    public static void enterUrl(String url) {
        driver.get(url);
    }

    public static void closeBrowser() {
        LOGGER.warning("Close browser");
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

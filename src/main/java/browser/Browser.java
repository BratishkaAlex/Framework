package browser;

import org.openqa.selenium.WebDriver;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.logging.Level;

public class Browser {

    private static WebDriver driver = null;

    private Browser() {
    }

    public static void setUp(String browser) {
        if (driver == null) {
            driver = BrowserFactory.getDriver(browser);
        }
    }

    public static void maximize() {
        LoggerUtil.LOGGER.log(Level.INFO, "Maximize window");
        driver.manage().window().maximize();
    }

    public static void changeTab(int expectedNumber) {
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(expectedNumber));
    }

    public static void enterUrl(String url) {

        driver.get(url);
    }

    public static void closeBrowser() {
        LoggerUtil.LOGGER.log(Level.INFO, "Close browser");
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

package browser;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

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
        driver.manage().window().maximize();
    }

    public static void changeTab(int expectedNumber) {
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(expectedNumber));
    }

    public static void enterUrl(String url) {
        driver.get(url);
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

package framework.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static framework.utils.LoggerUtil.LOGGER;

class BrowserFactory {
    static WebDriver getDriver(String browser, MutableCapabilities options) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                LOGGER.info("Creating instance of ChromeDriver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                LOGGER.info("Creating instance of FirefoxDriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Wrong browser name!");
        }
        return driver;
    }
}

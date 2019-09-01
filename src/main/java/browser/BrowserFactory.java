package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.LoggerUtil;
import utils.PropertyManager;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

class BrowserFactory {
    static WebDriver getDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                LoggerUtil.LOGGER.log(Level.INFO, "Creating instance of chromedriver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getPropForChrome());
                break;
            case "firefox":
                LoggerUtil.LOGGER.log(Level.INFO, "Creating instance of firefoxdriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getPropsForFirefox());
                break;
            default:
                throw new IllegalArgumentException("Wrong browser name!");
        }
        return driver;
    }

    private static DesiredCapabilities getPropForChrome() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", new File(PropertyManager.getProperty("path")).getAbsolutePath());
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("intl.accept_languages", PropertyManager.getProperty("language"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities chromeOptions = DesiredCapabilities.chrome();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, options);
        return chromeOptions;
    }

    private static FirefoxOptions getPropsForFirefox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("intl.accept_languages", PropertyManager.getProperty("language"));
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package, application/octet-stream");
        firefoxOptions.addPreference("browser.download.folderList",2);
        firefoxOptions.addPreference("browser.download.dir", new File(PropertyManager.getProperty("path")).getAbsolutePath());
        return firefoxOptions;
    }


}

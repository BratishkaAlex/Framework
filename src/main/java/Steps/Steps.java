package Steps;

import appUtils.PropertiesForBrowser;
import appUtils.Waiter;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import steamPages.CheckAgePage;

import static appUtils.LoggerUtil.LOGGER;

public class Steps {
    public static void confirmAge() {
        CheckAgePage checkAgePage = new CheckAgePage();
        checkAgePage.selectRightAge();
        checkAgePage.confirmAge();
    }

    public static void setUpBrowser() {
        LOGGER.info("Creating instance of webDriver");
        String browser = PropertyManager.getProperty("src/main/resources/config.properties", "browser");
        Browser.setUp(browser, PropertiesForBrowser.getOptions(browser));
        LOGGER.info("Maximize window");
        Browser.maximize();
        Waiter.implicitWait();
    }

    public static void closeBrowser() {
        LOGGER.info("Close browser");
        Browser.closeBrowser();
    }
}

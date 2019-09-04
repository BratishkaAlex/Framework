package Steps;

import appUtils.PropertiesForBrowser;
import appUtils.Waiter;
import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.PropertyManager;
import steamPages.CheckAgePage;

public class Steps {
    public static void confirmAge() {
        CheckAgePage checkAgePage = new CheckAgePage();
        checkAgePage.selectRightAge();
        checkAgePage.confirmAge();
    }

    public static void setUpBrowser() {
        new LoggerUtil("src/main/resources/log.config");
        String browser = PropertyManager.getProperty("src/main/resources/config.properties", "browser");
        Browser.setUp(browser, PropertiesForBrowser.getOptions(browser));
        Browser.maximize();
        Waiter.implicitWait();
    }

    public static void closeBrowser() {
        Browser.closeBrowser();
    }
}

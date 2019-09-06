package Steps;

import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.PropertyManager;
import framework.utils.Waiter;
import pageObject.steamPages.CheckAgePage;

public class Steps {
    public static void confirmAge() {
        CheckAgePage checkAgePage = new CheckAgePage();
        checkAgePage.selectRightAge();
        checkAgePage.confirmAge();
    }

    public static void setUpBrowser() {
        Browser.maximize();
        Waiter.implicitWait(Integer.parseInt(PropertyManager.getConfigProperty("timeout")));
    }

    public static void setUpLogger(String className) {
        new LoggerUtil(className);
    }
}

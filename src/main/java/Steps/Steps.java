package Steps;

import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.Waiter;
import pageObject.steamPages.CheckAgePage;

public class Steps {
    public static void confirmAge() {
        CheckAgePage checkAgePage = new CheckAgePage();
        checkAgePage.selectRightAge();
        checkAgePage.confirmAge();
    }

    public static void setUpBrowser() {
        new LoggerUtil("src/main/resources/log.config");
        Browser.maximize();
        Waiter.implicitWaitDefault();
    }


    public static void closeBrowser() {
        Browser.closeBrowser();
    }
}

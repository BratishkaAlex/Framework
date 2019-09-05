package appUtils;

import framework.browser.Browser;
import org.openqa.selenium.By;

import static framework.utils.LoggerUtil.LOGGER;

public class Utils {

    public static String getFilename() {
        LOGGER.info("Get filename according to OS");
        switch (System.getProperty("os.name")) {
            case "Linux":
                return "steam_latest.deb";
            case "Windows 10":
                return "SteamSetup.exe";
            default:
                throw new IllegalArgumentException("Unknown OS");
        }
    }

    public static boolean elementIsDisplayed(By loc) {
        return Browser.getDriver().findElement(loc).isDisplayed();
    }
}

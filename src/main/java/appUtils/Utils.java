package appUtils;

import framework.browser.Browser;
import framework.utils.LoggerUtil;
import org.openqa.selenium.By;

import java.util.logging.Level;

public class Utils {

    public static String getFilename() {
        LoggerUtil.LOGGER.log(Level.INFO, "Get filename according to OS");
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

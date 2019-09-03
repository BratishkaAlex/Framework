package appUtils;

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

}

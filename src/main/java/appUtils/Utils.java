package appUtils;

import framework.utils.PropertyManager;

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

    public static String getGenre(String genre){
        switch (PropertyManager.getProperty("src/main/resources/config.properties", "language")) {
            case "ru":
                return PropertyManager.getProperty("src/main/resources/ru.properties", genre);
            case "en":
                return PropertyManager.getProperty("src/main/resources/en.properties", genre);
            default:
                throw new IllegalArgumentException("Unknown language");
        }
    }
}

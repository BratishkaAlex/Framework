package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class PropertyManager {

    public static String getProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("src/main/resources/config.properties")));
        } catch (FileNotFoundException e) {
            System.out.println("Config.property wasn't found");
        } catch (IOException e) {
            System.out.println("Error in reading config.property");
        }
        return properties.getProperty(property);
    }

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

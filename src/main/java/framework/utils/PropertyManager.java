package framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.exception;

public class PropertyManager {

    public static String getProperty(String pathToPropertiesFile, String property) {
        LOGGER.warning("Reading property " + property + " from .properties file, can be FileNotFoundException or IOException");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(pathToPropertiesFile)));
        } catch (FileNotFoundException e) {
            exception("FileNotFoundException", e);
            System.out.println("Config.property wasn't found");
        } catch (IOException e) {
            exception("IOException", e);
            System.out.println("Error in reading .property file");
        }
        return properties.getProperty(property);
    }

}

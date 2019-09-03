package framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    public static String getProperty(String pathToPropertiesFile, String property) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(pathToPropertiesFile)));
        } catch (FileNotFoundException e) {
            System.out.println("Config.property wasn't found");
        } catch (IOException e) {
            System.out.println("Error in reading .property file");
        }
        return properties.getProperty(property);
    }

}

package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerUtil {

    public static Logger LOGGER;

    static {
        try (FileInputStream ins = new FileInputStream("src/main/resources/log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = java.util.logging.Logger.getLogger("TestLogger");
        } catch (IOException e) {
            System.out.println("Error in reading config logger file");
        }
    }
}

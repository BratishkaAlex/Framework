package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerUtil {

    public static Logger LOGGER;

    public LoggerUtil(String pathToConfig) {
        try (FileInputStream ins = new FileInputStream(pathToConfig)) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = java.util.logging.Logger.getLogger("TestLogger");
        } catch (IOException e) {
            System.out.println("Error in reading config logger file");
        }
    }

    public static void step(String message, int counter) {
        LOGGER.info(String.format("\n<<<<<<<<Step %d>>>>>>>>>>>>.\n%s\n", counter, message));
    }

    public static void exception(String message, Exception e) {
        LOGGER.log(Level.WARNING, message, e);
    }

}

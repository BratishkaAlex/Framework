package appUtils;

import com.google.common.base.Function;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Waiter {
    public static void implicitWait() {
        WebDriver driver = Browser.getDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyManager.getProperty("src/main/resources/config.properties", "timeout")), TimeUnit.SECONDS);
    }

    public static void waitForClickAble(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Integer.parseInt(PropertyManager.getProperty("src/main/resources/config.properties", "timeout")));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void waitForFile(File file) {
        FluentWait wait = new FluentWait(Browser.getDriver()).withTimeout(Integer.parseInt(PropertyManager.getProperty("src/main/resources/config.properties", "timeout")), TimeUnit.SECONDS).
                pollingEvery(1, TimeUnit.SECONDS);
        wait.until((Function) (webDriver) -> file.exists());
    }
}

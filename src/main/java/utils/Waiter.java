package utils;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waiter {
    public static void implicitWait() {
        WebDriver driver = Browser.getDriver();
        driver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertyManager.getProperty("timeout")), TimeUnit.SECONDS);
    }

    public static void waitForClickable(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Integer.valueOf(PropertyManager.getProperty("timeout")));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   /* public void waitForFile(WebDriver driver, File file) {
        FluentWait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).
            pollingEvery(1, TimeUnit.SECONDS);
        wait.until((webDriver) -> file.exists());
    }*/
}

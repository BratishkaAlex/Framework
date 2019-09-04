package steamElements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.exception;

public class ConfirmAgeForm {

    private static By confirmAgeLoc = By.xpath("//div[@class='main_content_ctn']");

    public ConfirmAgeForm() {
    }

    public static boolean IsDisplayed() {
        LOGGER.warning("Checking existing confirm age page, can be NoSuchElementException");
        try {
            Browser.getDriver().findElement(confirmAgeLoc);
            return true;
        } catch (NoSuchElementException e) {
            exception("There is no such element", e);
            return false;
        }
    }
}

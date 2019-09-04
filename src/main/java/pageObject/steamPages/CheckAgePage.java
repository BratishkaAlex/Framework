package pageObject.steamPages;

import framework.browser.Browser;
import framework.elements.Button;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.exception;

public class CheckAgePage {
    private By rightAgeLoc = By.xpath("//select[@id='ageYear']//option[@value='1996']");
    private By viewPageLoc = By.xpath("//a[contains(@class, 'btn_medium')]");
    private static By confirmAgeLoc = By.xpath("//div[@class='main_content_ctn']");

    private Button getRightAge() {
        return new Button(rightAgeLoc);
    }

    public void selectRightAge() {
        getRightAge().click();
    }

    private Button getViewPageButton() {
        return new Button(viewPageLoc);
    }

    public void confirmAge() {
        getViewPageButton().click();
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

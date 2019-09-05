package pageObject.steamPages;

import appUtils.Utils;
import framework.elements.Button;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static framework.utils.LoggerUtil.LOGGER;

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
        Waiter.implicitWait(1);
        LOGGER.warn("Checking existing confirm age page, can be NoSuchElementException");
        try {
            boolean isDisplayed = Utils.elementIsDisplayed(confirmAgeLoc);
            Waiter.implicitWaitDefault();
            return isDisplayed;
        } catch (NoSuchElementException e) {
            LOGGER.error("There is no such element", e);
            Waiter.implicitWaitDefault();
            return false;
        }
    }
}

package steamElements;

import framework.browser.Browser;
import framework.elements.BaseElement;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Button extends BaseElement {
    public Button(By loc) {
        super(loc);
    }

    public Button(WebElement webElement, By loc) {
        super(loc);
        super.setWebElement(webElement);
    }

    public void click() {
        Waiter.waitForClickAble(super.getLoc());
        super.getWebElement().click();
    }

    public static List<WebElement> getListElements(By loc) {
        return Browser.getDriver().findElements(loc);
    }
}

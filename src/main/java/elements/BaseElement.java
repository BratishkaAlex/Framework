package elements;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Waiter;

public abstract class BaseElement {
    private By loc;
    private WebElement webElement;

    public BaseElement(By loc) {
        this.loc = loc;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    public void click() {
        Waiter.waitForClickable(loc);
        webElement.click();
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

}

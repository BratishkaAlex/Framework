package elements;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseForm {
    private By loc;
    private WebElement webElement;
    private static List<? extends BaseElement> listElements;

    public BaseForm(By loc) {
        this.loc = loc;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

}

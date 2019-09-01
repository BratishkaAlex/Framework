package elements;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseForm {
    private WebElement webElement;

    public BaseForm(By loc) {
        this.webElement = Browser.getDriver().findElement(loc);
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

}

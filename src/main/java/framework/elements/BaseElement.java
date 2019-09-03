package framework.elements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    private By loc;
    private WebElement webElement;

    public BaseElement(By loc) {
        this.loc = loc;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    protected void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public String getText() {
        return webElement.getText();
    }

    public WebElement findElement(By loc) {
        return webElement.findElement(loc);
    }

    protected WebElement getWebElement() {
        return this.webElement;
    }

    protected By getLoc() {
        return this.loc;
    }
}
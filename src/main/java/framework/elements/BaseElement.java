package framework.elements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static framework.utils.LoggerUtil.LOGGER;

public abstract class BaseElement {
    private By loc;
    private WebElement webElement;

    public BaseElement(By loc) {
        LOGGER.info(String.format("Creating instance of custom %s", this.getClass().getSimpleName()));
        this.loc = loc;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    protected void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        LOGGER.info(String.format("Checking displaying of custom %s", this.getClass().getSimpleName()));
        return webElement.isDisplayed();
    }

    public String getText() {
        LOGGER.info(String.format("Getting text of custom %s", this.getClass().getSimpleName()));
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

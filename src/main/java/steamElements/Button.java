package steamElements;

import browser.Browser;
import elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Button extends BaseElement {
    public Button(By loc) {
        super(loc);
    }
    public Button(WebElement webElement) {
        super(webElement);
    }
    public static List<WebElement> getListElements(By loc) {
        return Browser.getDriver().findElements(loc);
    }
}

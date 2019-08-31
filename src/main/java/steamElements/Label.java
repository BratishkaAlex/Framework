package steamElements;

import browser.Browser;
import elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Label extends BaseElement {
    public Label(By loc) {
        super(loc);
    }

    public static List<WebElement> getListElements(By loc) {
        return Browser.getDriver().findElements(loc);
    }

}

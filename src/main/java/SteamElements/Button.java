package SteamElements;

import elements.BaseElement;
import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By loc) {
        super(loc);
    }

    public String getHref(){
        return getWebElement().getAttribute("href");
    }
}

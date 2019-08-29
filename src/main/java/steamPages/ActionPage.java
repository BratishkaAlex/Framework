package steamPages;

import SteamElements.Button;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionPage {
    private WebDriver webDriver;
    private By topSpellingLoc = By.id("tab_select_TopSellers");
    private By listDiscounts = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");

    public ActionPage() {
        webDriver = Browser.getDriver();
    }

    private Button getTopSpelling() {
        return new Button(topSpellingLoc);
    }

    public void clickOnTopSpelling() {
        getTopSpelling().click();
    }

}

package steamPages;

import steamElements.Banner;
import steamElements.Button;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utils.Waiter;

public class HomePage {
    private By homePageBannerLoc = By.xpath("//div[@class='home_page_content']");
    private By installButtonLoc = By.xpath("//a[@class='header_installsteam_btn_content']");
    private By listGamesLocReserve = By.id("genre_tab");
    private By actionLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Action') or contains(text(), 'Экшен'))]");
    private By indieLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Indie') or contains(text(), 'Инди'))]");

    private By listGamesLoc = By.xpath("//div[@id='genre_tab']//span[@class='pulldown']//span");

    public void clickOnInstall() {
        getInstallButton().click();
    }

    private Button getInstallButton() {
        return new Button(installButtonLoc);
    }


    public void selectActionCategory() {
        openGamesList();
        selectAction().click();
    }

    public void selectIndieCategory() {
        openGamesList();
        selectIndie().click();
    }

    private void openGamesList() {
       //// Waiter.waitForClickable(listGamesLoc);
      //  new Actions(Browser.getDriver()).moveToElement(new Button(listGamesLoc).getWebElement()).perform();
        new Button(listGamesLoc).click();
    }

    private Button selectAction() {
        return new Button(actionLoc);
    }

    private Button selectIndie() {
        return new Button(indieLoc);
    }

    public boolean isHomePage() {
        return new Banner(homePageBannerLoc).isDisplayed();
    }
}

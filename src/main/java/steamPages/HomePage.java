package steamPages;

import SteamElements.Banner;
import SteamElements.Button;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private WebDriver driver;
    private By homePageBannerLoc = By.xpath("//div[@class='home_page_content']");
    private By installButtonLoc = By.xpath("//a[@class='header_installsteam_btn_content']");
    private By listGamesLoc = By.id("genre_tab");
    private By actionLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Action') or contains(text(), 'Экшен'))]");

    public HomePage() {
        driver = Browser.getDriver();
    }

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

    private void openGamesList() {
        new Actions(driver).moveToElement(new Button(listGamesLoc).getWebElement()).perform();
    }

    private Button selectAction() {
        return new Button(actionLoc);
    }

    public boolean isHomePage(){
        return new Banner(homePageBannerLoc).isDisplayed();
    }
}

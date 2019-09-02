package steamPages;

import steamElements.*;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utils.Waiter;

public class HomePage {
    private GlobalMenu globalMenu;
    public GenreMenu genreMenu;
    public NavigationMenu navigationMenu;
    private By homePageBannerLoc = By.xpath("//div[@class='home_page_content']");

    public HomePage(){
        globalMenu = new GlobalMenu();
        genreMenu = new GenreMenu();
        navigationMenu = new NavigationMenu();
    }

    public boolean isHomePage() {
        return new Banner(homePageBannerLoc).isDisplayed();
    }

    public GlobalMenu getGlobalMenu() {
        return globalMenu;
    }


}

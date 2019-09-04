package pageObject.steamPages;

import org.openqa.selenium.By;
import pageObject.steamForms.GenreMenu;
import pageObject.steamForms.GlobalMenu;
import pageObject.steamForms.NavigationMenu;
import steamElements.Banner;

public class HomePage {
    private GlobalMenu globalMenu;
    private GenreMenu genreMenu;
    private NavigationMenu navigationMenu;
    private By homePageBannerLoc = By.xpath("//div[@class='home_page_content']");

    public HomePage() {
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

    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    public GenreMenu getGenreMenu() {
        return genreMenu;
    }
}

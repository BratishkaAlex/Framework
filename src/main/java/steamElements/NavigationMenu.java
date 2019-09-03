package steamElements;

import org.openqa.selenium.By;

public class NavigationMenu {
    private By listGamesLoc = By.xpath("//div[@id='genre_tab']//span[@class='pulldown']//span");

    public void clickOnGenreTab(){
        new Button(listGamesLoc).click();
    }

}

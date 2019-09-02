package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;

public class NavigationMenu extends BaseForm {
    private By listGamesLoc = By.xpath("//div[@id='genre_tab']//span[@class='pulldown']//span");

    public NavigationMenu(){

    }

    public void clickOnGenreTab(){
        new Button(listGamesLoc).click();
    }

}

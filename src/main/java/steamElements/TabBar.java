package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;

public class TabBar extends BaseForm {
    private By topSpellingLoc = By.id("tab_select_TopSellers");

    public TabBar(){

    }

    private Button getTabButton(){
        return new Button(topSpellingLoc);
    }

    public void navigateToTopSellers(){
        getTabButton().click();
    }
}

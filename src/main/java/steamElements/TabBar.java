package steamElements;

import org.openqa.selenium.By;

public class TabBar {
    private By topSellingLoc = By.id("tab_select_TopSellers");

    private Button getTabButton() {
        return new Button(topSellingLoc);
    }

    public void navigateToTopSellers() {
        getTabButton().click();
    }
}

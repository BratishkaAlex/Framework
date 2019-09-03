package steamPages;

import org.openqa.selenium.By;
import steamElements.Button;

public class CheckAgePage {
    private By rightAgeLoc = By.xpath("//select[@id='ageYear']//option[@value='1996']");
    private By viewPageLoc = By.xpath("//a[@class='btnv6_blue_hoverfade btn_medium']");

    private Button getRightAge() {
        return new Button(rightAgeLoc);
    }

    public void selectRightAge() {
        getRightAge().click();
    }

    private Button getViewPageButton() {
        return new Button(viewPageLoc);
    }

    public void confirmAge() {
        getViewPageButton().click();
    }
}

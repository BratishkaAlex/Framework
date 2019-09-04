package pageObject.steamForms;

import framework.elements.Button;
import org.openqa.selenium.By;

public class GenreMenu {
    private String pattern = "//a[@class='popup_menu_item' and contains(text(), '%s')]";

    private Button getGenreButton(String param) {
        return new Button(By.xpath(String.format(pattern, param)));
    }

    public void navigateTo(String param) {
        getGenreButton(param).click();
    }
}

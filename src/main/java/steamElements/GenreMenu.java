package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;

public class GenreMenu extends BaseForm {
    private String pattern = "//a[@class='popup_menu_item' and contains(text(), '%s')]";
    private By actionLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Action') or contains(text(), 'Экшен'))]");
    private By indieLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Indie') or contains(text(), 'Инди'))]");

    public GenreMenu(){
    }

    private Button getGenreButton(String param){
        return new Button(By.xpath(String.format(pattern, param)));
    }

    public void navigateTo(String param){
        getGenreButton(param).click();
    }
}

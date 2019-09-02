package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;

public class GenreMenu extends BaseForm {
    private String pattern = "//a[@class='popup_menu_item' and contains(text(), '%s')]";
    private By actionLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Action') or contains(text(), 'Экшен'))]");
    private By indieLoc = By.xpath("//a[@class='popup_menu_item' and (contains(text(), 'Indie') or contains(text(), 'Инди'))]");

    public GenreMenu(){
        addElement("Action", new Button(actionLoc));
        addElement("Indie", new Button(indieLoc));
    }

    public Button navigateTo(String param){
        String loc = String.format(pattern, param);
        return new Button(By.xpath(loc));
    }
}

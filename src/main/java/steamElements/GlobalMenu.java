package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;

public class GlobalMenu extends BaseForm {
    private By installButtonLoc = By.xpath("//a[@class='header_installsteam_btn_content']");

    public GlobalMenu(){
        addElement("installSteam", new Button(installButtonLoc));
    }

    public void goToIstallationPage(){
        new Button(installButtonLoc).click();
    }
}

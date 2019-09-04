package pageObject.steamForms;

import framework.elements.Button;
import org.openqa.selenium.By;

public class GlobalMenu {
    private By installButtonLoc = By.xpath("//a[@class='header_installsteam_btn_content']");

    public void goToInstallationPage() {
        getInstallSteamBtn().click();
    }

    private Button getInstallSteamBtn() {
        return new Button(installButtonLoc);
    }
}

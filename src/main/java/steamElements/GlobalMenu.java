package steamElements;

import org.openqa.selenium.By;

public class GlobalMenu {
    private By installButtonLoc = By.xpath("//a[@class='header_installsteam_btn_content']");

    public void goToInstallationPage() {
        new Button(installButtonLoc).click();
    }
}

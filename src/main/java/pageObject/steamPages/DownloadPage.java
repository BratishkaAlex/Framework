package pageObject.steamPages;

import framework.elements.Button;
import org.openqa.selenium.By;

public class DownloadPage {
    private By downloadBtn = By.xpath("//a[@class='about_install_steam_link']");

    public void downloadSteam() {
        getDownloadButton().click();
    }

    private Button getDownloadButton() {
        return new Button(downloadBtn);
    }

    public boolean isDownloadPage() {
        return getDownloadButton().isDisplayed();
    }
}

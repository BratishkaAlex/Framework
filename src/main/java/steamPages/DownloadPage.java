package steamPages;

import SteamElements.Button;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadPage {
    private WebDriver webDriver;
    private By downloadBtn = By.xpath("//a[@class='about_install_steam_link']");

    public DownloadPage(){
        webDriver = Browser.getDriver();
    }

    public void downloadSteam(){
        getDownloadButton().click();
    }

    private Button getDownloadButton() {
        return new Button(downloadBtn);
    }

    public String getDownloadButtonHref(){
        return new Button(downloadBtn).getHref();
    }
}

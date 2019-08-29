import browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.DownloadPage;
import steamPages.HomePage;
import utils.FileDownloader;
import utils.Props;
import utils.Waiter;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class SteamTest {

    @BeforeTest
    public void setUp() {
        Browser.setUp(Props.getProperty("browser"));
        Browser.enterUrl(Props.getProperty("url"));
        Browser.maximize();
        Waiter.implicitWait(30);
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    @Test
    public void testCase1() {
        HomePage homePage = new HomePage();
        homePage.clickOnInstall();
        DownloadPage downloadPage = new DownloadPage();
        downloadPage.downloadSteam();
        FileDownloader fileDownloader = new FileDownloader(Browser.getDriver());
        try {
            fileDownloader.setURI(downloadPage.getDownloadButtonHref());
            File secretFile = fileDownloader.downloadFile();
            int httpStatusCode = fileDownloader.getLastDownloadHTTPStatus();
            assertEquals(httpStatusCode, 200);
        } catch (Exception e) {

        }

    }
    /*@Test
    public void testCase2() {
        HomePage homePage = new HomePage();
        homePage.selectActionCategory();
        ActionPage actionPage = new ActionPage();
        actionPage.clickOnTopSpelling();
    }*/

    public void testDownload() {

    }
}

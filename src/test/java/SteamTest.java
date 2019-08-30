import browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.ActionPage;
import steamPages.DownloadPage;
import steamPages.HomePage;
import utils.Props;
import utils.Waiter;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class SteamTest {

    @BeforeTest
    public void setUp() {
        Browser.setUp(Props.getProperty("browser"));
        Browser.enterUrl(Props.getProperty("url"));
        Browser.maximize();
        Waiter.implicitWait(30);
    }

//    @AfterTest
//    public void closeBrowser() {Browser.closeBrowser();
//    }

    @AfterTest
    public void deleteFile() {
        System.out.println("First");
        new File(Props.getProperty("path"), Props.getProperty("filename") + getExtension()).delete();
    }


    public void testCase1() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.clickOnInstall();

        DownloadPage downloadPage = new DownloadPage();
        assertTrue(downloadPage.isDownloadPage(), "This is not the download page");
        downloadPage.downloadSteam();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        File downloadFile = new File(Props.getProperty("path"), Props.getProperty("filename") + getExtension());
        assertTrue(downloadFile.exists(), "There is no such file");
    }

    @Test
    public void testCase2() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.selectActionCategory();

        ActionPage actionPage = new ActionPage();
        actionPage.clickOnTopSpelling();
        actionPage.clickOnGame();
    }

    private String getExtension() {
        switch (System.getProperty("os.name")) {
            case "Linux":
                return ".deb";
            case "Windows 10":
                return ".exe";
            default:
                throw new IllegalArgumentException("Unknown OS");
        }
    }
}

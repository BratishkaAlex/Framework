import browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.DownloadPage;
import steamPages.HomePage;
import utils.LoggerUtil;
import utils.PropertyManager;
import utils.Waiter;

import java.io.File;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;

public class SteamTestCase1 {

    @BeforeTest
    public void setUp() {
        Browser.setUp(PropertyManager.getProperty("browser"));
        Browser.maximize();
        Waiter.implicitWait();
    }

    @BeforeMethod
    public void enterUrl() {
        LoggerUtil.LOGGER.log(Level.INFO, "Go to the main Steam page");
        Browser.enterUrl(PropertyManager.getProperty("url"));
    }

    @AfterTest
    public void closeBrowser() {
        LoggerUtil.LOGGER.log(Level.INFO, "Close browser");
        Browser.closeBrowser();
        LoggerUtil.LOGGER.log(Level.INFO, "Delete downloaded file");
        new File(PropertyManager.getProperty("path"), PropertyManager.getFilename()).delete();
    }

    @Test
    public void testCase1() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.getGlobalMenu().goToIstallationPage();

        DownloadPage downloadPage = new DownloadPage();
        assertTrue(downloadPage.isDownloadPage(), "This is not the download page");
        downloadPage.downloadSteam();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        File downloadFile = new File(PropertyManager.getProperty("path"), PropertyManager.getFilename());
        assertTrue(downloadFile.exists(), "There is no such file");
    }

}

import appUtils.LoggerUtil;
import appUtils.Utils;
import framework.utils.PropertyManager;
import framework.utils.Waiter;
import framework.browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.DownloadPage;
import steamPages.HomePage;

import java.io.File;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;

public class SteamTestCase1 {

    @BeforeTest
    public void setUp() {
        LoggerUtil.LOGGER.log(Level.INFO, "Creating instance of webDriver");
        Browser.setUp(PropertyManager.getProperty("src/main/resources/config.properties", "browser"));
        LoggerUtil.LOGGER.log(Level.INFO, "Maximize window");
        Browser.maximize();
        Waiter.implicitWait();
        LoggerUtil.LOGGER.log(Level.INFO, "Delete downloaded file, if it exists");
        File downloadFile = new File(PropertyManager.getProperty("src/main/resources/config.properties", "path"), Utils.getFilename());
        if (downloadFile.exists()) {
            downloadFile.delete();
        }
    }

    @BeforeMethod
    public void enterUrl() {
        LoggerUtil.LOGGER.log(Level.INFO, "Go to the main Steam page");
        Browser.enterUrl(PropertyManager.getProperty("src/main/resources/config.properties", "url"));
    }

    @AfterTest
    public void closeBrowser() {
        LoggerUtil.LOGGER.log(Level.INFO, "Close browser");
        Browser.closeBrowser();
    }

    @Test
    public void testCase1() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.getGlobalMenu().goToInstallationPage();

        DownloadPage downloadPage = new DownloadPage();
        assertTrue(downloadPage.isDownloadPage(), "This is not the download page");
        downloadPage.downloadSteam();


        File downloadFile = new File(PropertyManager.getProperty("src/main/resources/config.properties", "path"), Utils.getFilename());
        Waiter.waitForFile(downloadFile);
        assertTrue(downloadFile.exists(), "There is no such file");
    }

}

import Steps.Steps;
import appUtils.LoggerUtil;
import appUtils.Utils;
import appUtils.Waiter;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.DownloadPage;
import steamPages.HomePage;

import java.io.File;
import java.util.logging.Level;

import static appUtils.LoggerUtil.LOGGER;
import static org.testng.Assert.assertTrue;

public class SteamTestCase1 {

    @BeforeTest
    public void setUp() {
        Steps.setUpBrowser();
        LOGGER.info("Delete downloaded file, if it exists");
        File downloadFile = new File(PropertyManager.getProperty("src/main/resources/config.properties", "path"), Utils.getFilename());
        if (downloadFile.exists()) {
            downloadFile.delete();
        }
    }

    @AfterTest
    public void closeBrowser() {
        Steps.closeBrowser();
    }

    @Test
    public void testCase1() {
        LOGGER.info("Step 1. Open http://store.steampowered.com/");
        Browser.enterUrl(PropertyManager.getProperty("src/main/resources/config.properties", "url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LoggerUtil.LOGGER.log(Level.INFO, "Step 2. Click “Install Steam” button");
        homePage.getGlobalMenu().goToInstallationPage();

        DownloadPage downloadPage = new DownloadPage();
        LOGGER.info("Check Steam store download page is opened");
        assertTrue(downloadPage.isDownloadPage(), "This is not the download page");
        LoggerUtil.LOGGER.log(Level.INFO, "Step 3. Click “Install Steam Now” button -> download steam app");
        downloadPage.downloadSteam();

        LOGGER.info("Check Steam app setup file is downloaded");
        File downloadFile = new File(PropertyManager.getProperty("src/main/resources/config.properties", "path"), Utils.getFilename());
        Waiter.waitForFile(downloadFile);
        assertTrue(downloadFile.exists(), "There is no such file");
    }

}

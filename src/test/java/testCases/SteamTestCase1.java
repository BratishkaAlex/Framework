package testCases;

import Steps.Steps;
import appUtils.Utils;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import framework.utils.Waiter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.steamPages.DownloadPage;
import pageObject.steamPages.HomePage;

import java.io.File;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertTrue;

public class SteamTestCase1 {
    int counterSteps = 1;

    @BeforeTest
    public void setUp() {
        Steps.setUpBrowser();
        LOGGER.info("Delete downloaded file, if it exists");
        File downloadFile = new File(PropertyManager.getConfigProperty("path"), Utils.getFilename());
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
        step("Open http://store.steampowered.com/", counterSteps++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        step("Click “Install Steam” button", counterSteps++);
        homePage.getGlobalMenu().goToInstallationPage();

        DownloadPage downloadPage = new DownloadPage();
        LOGGER.info("Check Steam store download page is opened");
        assertTrue(downloadPage.isDownloadPage(), "This is not the download page");
        step("Click “Install Steam Now” button -> download steam app", counterSteps++);
        downloadPage.downloadSteam();

        LOGGER.info("Check Steam app setup file is downloaded");
        File downloadFile = new File(PropertyManager.getConfigProperty("path"), Utils.getFilename());
        Waiter.waitForFile(downloadFile);
        assertTrue(downloadFile.exists(), "There is no such file");
    }

}

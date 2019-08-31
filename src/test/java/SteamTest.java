import browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steamPages.ActionPage;
import steamPages.DownloadPage;
import steamPages.GamePage;
import steamPages.HomePage;
import steamPages.IndiePage;
import utils.LoggerUtil;
import utils.Props;
import utils.Waiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTest {

    @BeforeMethod
    public void setUp() {
        Browser.setUp(Props.getProperty("browser"));
        LoggerUtil.LOGGER.log(Level.INFO, "Go to the main Steam page");
        Browser.enterUrl(Props.getProperty("url"));
        Browser.maximize();
        Waiter.implicitWait(30);
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    @AfterTest
    public void deleteFile() {
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
        LoggerUtil.LOGGER.log(Level.INFO, "Check opening the main page");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on action category");
        homePage.selectActionCategory();

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the action game's page");
        ActionPage actionPage = new ActionPage();
        assertTrue(actionPage.isActionGamesPage(), "This is not the action games page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on top spellers");
        actionPage.clickOnTopSpelling();
        assertTrue(actionPage.isTopSpellersClicked(), "Didn't click on top spellers");
        LoggerUtil.LOGGER.log(Level.INFO, "Save the game's with max discount name, discount, original and final prices");
        String gameWithMaxDiscount = actionPage.getGameName();
        int discount = actionPage.getDiscount();
        double originalPrice = actionPage.getOriginalPrice();
        double finalPrice = actionPage.getFinalPrice();
        LoggerUtil.LOGGER.log(Level.INFO, "Click on chosen game");
        actionPage.clickOnGame();

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the chosen game's page");
        GamePage gamePage = new GamePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Checking all saved properties with properties from current page");
        assertTrue(gamePage.getGameName().trim().toLowerCase().contains(gameWithMaxDiscount.toLowerCase().trim().toLowerCase()), "This is not the chosen game's page");
        //assertEquals(gameWithMaxDiscount.toLowerCase().trim(), gamePage.getGameName().toLowerCase().trim(), "This is not the chosen game's page");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }

    @Test
    public void testCase3() {
        HomePage homePage = new HomePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Check opening the main page");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on indie category");
        homePage.selectIndieCategory();

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the chosen game's page");
        IndiePage indiePage = new IndiePage();
        assertTrue(indiePage.isActionGamesPage(), "This is not the indie games page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on top spellers");
        indiePage.clickOnTopSpelling();
        assertTrue(indiePage.isTopSpellersClicked(), "Didn't click on top spellers");
        LoggerUtil.LOGGER.log(Level.INFO, "Save the game's with min discount name, discount, original and final prices");
        String gameWithMinDiscount = indiePage.getGameName();
        int discount = indiePage.getDiscount();
        double originalPrice = indiePage.getOriginalPrice();
        double finalPrice = indiePage.getFinalPrice();
        LoggerUtil.LOGGER.log(Level.INFO, "Click on chosen game");
        indiePage.clickOnGame();

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the chosen game's page");
        GamePage gamePage = new GamePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Checking all saved properties with properties from current page");
        assertTrue(gamePage.getGameName().toLowerCase().trim().contains(gameWithMinDiscount.toLowerCase().trim()), "This is not the chosen game's page");
        //assertEquals(gameWithMinDiscount.toLowerCase().trim(), gamePage.getGameName().toLowerCase().trim(), "This is not the chosen game's page");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }

    private String getExtension() {
        LoggerUtil.LOGGER.log(Level.INFO, "Get extension according to OS");
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

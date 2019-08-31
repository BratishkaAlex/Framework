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
import utils.Props;
import utils.Waiter;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTest {

    @BeforeMethod
    public void setUp() {
        Browser.setUp(Props.getProperty("browser"));
        Browser.enterUrl(Props.getProperty("url"));
        Browser.maximize();
        Waiter.implicitWait(30);
    }

    @AfterMethod
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
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.selectActionCategory();

        ActionPage actionPage = new ActionPage();
        assertTrue(actionPage.isActionGamesPage(), "This is not the action games page");
        actionPage.clickOnTopSpelling();
        assertTrue(actionPage.isTopSpellersClicked(), "Didn't click on top spellers");
        String gameWithMaxDiscount = actionPage.getGameName();
        System.out.println(gameWithMaxDiscount);
        int discount = actionPage.getDiscount();
        double originalPrice = actionPage.getOriginalPrice();
        double finalPrice = actionPage.getFinalPrice();
        actionPage.clickOnGame();

        GamePage gamePage = new GamePage();
        System.out.println(gamePage.getGameName());
        assertTrue(gamePage.getGameName().trim().toLowerCase().contains(gameWithMaxDiscount.toLowerCase().trim().toLowerCase()), "This is not the chosen game's page");
        //assertEquals(gameWithMaxDiscount.toLowerCase().trim(), gamePage.getGameName().toLowerCase().trim(), "This is not the chosen game's page");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }

    @Test
    public void testCase3() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.isHomePage(), "This is not the home page");
        homePage.selectIndieCategory();

        IndiePage indiePage = new IndiePage();
        assertTrue(indiePage.isActionGamesPage(), "This is not the indie games page");
        indiePage.clickOnTopSpelling();
        assertTrue(indiePage.isTopSpellersClicked(), "Didn't click on top spellers");
        String gameWithMinDiscount = indiePage.getGameName();
        int discount = indiePage.getDiscount();
        double originalPrice = indiePage.getOriginalPrice();
        double finalPrice = indiePage.getFinalPrice();
        System.out.println(gameWithMinDiscount);
        System.out.println(discount);
        System.out.println(originalPrice);
        System.out.println(finalPrice);
        indiePage.clickOnGame();

        GamePage gamePage = new GamePage();
        assertTrue(gamePage.getGameName().toLowerCase().trim().contains(gameWithMinDiscount.toLowerCase().trim()), "This is not the chosen game's page");
        //assertEquals(gameWithMinDiscount.toLowerCase().trim(), gamePage.getGameName().toLowerCase().trim(), "This is not the chosen game's page");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
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

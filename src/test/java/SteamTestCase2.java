import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.ActionPage;
import steamPages.CheckAgePage;
import steamPages.GamePage;
import steamPages.HomePage;
import utils.LoggerUtil;
import utils.PropertyManager;
import utils.Waiter;

import java.io.File;
import java.util.logging.Level;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTestCase2 {

    private By confirmAgeLoc = By.xpath("//div[@class='main_content_ctn']");

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

    private void confirmAge() {
        CheckAgePage checkAgePage = new CheckAgePage();
        checkAgePage.selectRightAge();
        checkAgePage.confirmAge();
    }

    private boolean hasConfirmAgeForm() {
        try {
            Browser.getDriver().findElement(confirmAgeLoc);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String getAction(){
        switch(PropertyManager.getProperty("language")){
            case "ru": return "Экшен";
            case "en": return "Action";
            default: throw new IllegalArgumentException("Unkwown language");
        }
    }

    @Test
    public void testCase2() {
        HomePage homePage = new HomePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Check opening the main page");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on action category");
        homePage.navigationMenu.clickOnGenreTab();
        homePage.genreMenu.navigateTo("Action").click();


        LoggerUtil.LOGGER.log(Level.INFO, "Opening the action game's page");
        ActionPage actionPage = new ActionPage();
        assertTrue(actionPage.isActionGamesPage(), "This is not the action games page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on top spellers");
        actionPage.tabBar.navigateTo("TopSellersTab").click();
        assertTrue(actionPage.isTopSpellersClicked(), "Didn't click on top spellers");
        LoggerUtil.LOGGER.log(Level.INFO, "Save the game's with max discount name, discount, original and final prices");
        String gameWithMaxDiscount = actionPage.getGameName();
        int discount = actionPage.getDiscount();
        double originalPrice = actionPage.getOriginalPrice();
        double finalPrice = actionPage.getFinalPrice();
        LoggerUtil.LOGGER.log(Level.INFO, "Click on chosen game");
        actionPage.topSpellersTab.getGame("MaxDiscount").click();

      /*  if (hasConfirmAgeForm()){
            LoggerUtil.LOGGER.log(Level.INFO, "Confirm right age");
            confirmAge();
        }*/

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the chosen game's page");
        GamePage gamePage = new GamePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Checking all saved properties with properties from current page");
        assertTrue(gamePage.getGameName().trim().toLowerCase().contains(gameWithMaxDiscount.toLowerCase().trim().toLowerCase()), "This is not the chosen game's page");
        //assertEquals(gameWithMaxDiscount.toLowerCase().trim(), gamePage.getGameName().toLowerCase().trim(), "This is not the chosen game's page");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }
}

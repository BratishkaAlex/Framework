import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamPages.CheckAgePage;
import steamPages.GamePage;
import steamPages.GenrePage;
import steamPages.HomePage;
import appUtils.LoggerUtil;
import framework.utils.PropertyManager;
import framework.utils.Waiter;

import java.util.logging.Level;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTestCase2 {

    private By confirmAgeLoc = By.xpath("//div[@class='main_content_ctn']");

    @BeforeTest
    public void setUp() {
        LoggerUtil.LOGGER.log(Level.INFO, "Creating instance of webDriver");
        Browser.setUp(PropertyManager.getProperty("src/main/resources/config.properties","browser"));
        LoggerUtil.LOGGER.log(Level.INFO, "Maximize window");
        Browser.maximize();
        Waiter.implicitWait();
    }

    @BeforeMethod
    public void enterUrl() {
        LoggerUtil.LOGGER.log(Level.INFO, "Go to the main Steam page");
        Browser.enterUrl(PropertyManager.getProperty("src/main/resources/config.properties","url"));
    }

    @AfterTest
    public void closeBrowser() {
        LoggerUtil.LOGGER.log(Level.INFO, "Close browser");
        Browser.closeBrowser();
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

    private String getAction() {
        switch (PropertyManager.getProperty("src/main/resources/config.properties","language")) {
            case "ru":
                return "Экшен";
            case "en":
                return "Action";
            default:
                throw new IllegalArgumentException("Unknown language");
        }
    }

    @Test
    public void testCase2() {
        HomePage homePage = new HomePage();
        LoggerUtil.LOGGER.log(Level.INFO, "Check opening the main page");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on action category");
        homePage.navigationMenu.clickOnGenreTab();
        homePage.genreMenu.navigateTo(getAction());

        LoggerUtil.LOGGER.log(Level.INFO, "Opening the action game's page");
        GenrePage actionPage = new GenrePage();
        assertTrue(actionPage.isGenrePage(getAction()), "This is not the action games page");
        LoggerUtil.LOGGER.log(Level.INFO, "Click on top spellers");
        actionPage.tabBar.navigateToTopSellers();
        assertTrue(actionPage.isTopSellersClicked(), "Didn't click on top sellers");
        LoggerUtil.LOGGER.log(Level.INFO, "Save the game's with max discount name, discount, original and final prices");
        String gameWithMaxDiscount = actionPage.getNameOfGameWithMaxDiscount();
        int discount = actionPage.getMaxDiscount();
        double originalPrice = actionPage.getOriginalPriceOfGameWithMaxDiscount();
        double finalPrice = actionPage.getFinalPriceOfGameWithMaxDiscount();
        LoggerUtil.LOGGER.log(Level.INFO, "Click on chosen game");
        actionPage.topSellersTab.navigateTo("MaxDiscount");

        if (hasConfirmAgeForm()) {
            LoggerUtil.LOGGER.log(Level.INFO, "Confirm right age");
            confirmAge();
        }

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

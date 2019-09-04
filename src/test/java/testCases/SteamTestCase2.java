package testCases;

import Steps.Steps;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.steamForms.ConfirmAgeForm;
import pageObject.steamPages.GamePage;
import pageObject.steamPages.GenrePage;
import pageObject.steamPages.HomePage;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTestCase2 {

    int counterSteps = 1;

    @BeforeTest
    public void setUp() {
        Steps.setUpBrowser();
    }


    @AfterTest
    public void closeBrowser() {
        Steps.closeBrowser();
    }

    @Test
    public void testCase2() {
        step("Open http://store.steampowered.com/", counterSteps++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        step("Select Games -> Action in the top menu", counterSteps++);
        homePage.getNavigationMenu().clickOnGenreTab();
        homePage.getGenreMenu().navigateTo(PropertyManager.getWordFromDictionary("Action"));

        GenrePage actionPage = new GenrePage();
        LOGGER.info("Check 'Browsing Action' page is opened");
        assertTrue(actionPage.isGenrePage(PropertyManager.getWordFromDictionary("Action")), "This is not the action games page");
        step("Navigate to “Top Selling” tab", counterSteps++);
        actionPage.getTabBar().navigateToTopSellers();
        LOGGER.info("Check Top Selling tab is opened");
        assertTrue(actionPage.isTopSellersClicked(), "Didn't click on top sellers");
        LOGGER.info("Save the game's with max discount name, discount, original and final prices");
        String gameWithMaxDiscount = actionPage.getNameOfGameWithMaxDiscount();
        int discount = actionPage.getMaxDiscount();
        double originalPrice = actionPage.getOriginalPriceOfGameWithMaxDiscount();
        double finalPrice = actionPage.getFinalPriceOfGameWithMaxDiscount();
        step("Click the game with the highest discount on the 1 page of the games list." +
            "Enter correct age on the “Rated content” page if it’s shown", counterSteps++);
        actionPage.getTopSellersTab().navigateTo("MaxDiscount");

        if (ConfirmAgeForm.IsDisplayed()) {
            LOGGER.info("Confirm right age");
            Steps.confirmAge();
        }

        GamePage gamePage = new GamePage();
        LOGGER.info("Check selected game page is opened");
        assertTrue(gamePage.getGameName().toLowerCase().trim().contains(gameWithMaxDiscount.toLowerCase().trim()), "This is not the chosen game's page");
        step("Check that game discount rate, initial and discounted prices are the same as on the step 4", counterSteps++);
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }
}

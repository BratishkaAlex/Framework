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

public class SteamTestCase3 {

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
    public void testCase3() {
        step("Open http://store.steampowered.com/", counterSteps++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        step("Select Games -> Indie in the top menu", counterSteps++);
        homePage.getNavigationMenu().clickOnGenreTab();
        homePage.getGenreMenu().navigateTo(PropertyManager.getWordFromDictionary("Indie"));

        GenrePage indiePage = new GenrePage();
        LOGGER.info("Check 'Browsing Indie' page is opened");
        assertTrue(indiePage.isGenrePage(PropertyManager.getWordFromDictionary("Indie")), "This is not the indie games page");
        step("Navigate to “Top Selling” tab", counterSteps++);
        indiePage.getTabBar().navigateToTopSellers();
        LOGGER.info("Check Top Selling tab is opened");
        assertTrue(indiePage.isTopSellersClicked(), "Didn't click on top sellers");
        LOGGER.info("Save the game's with min discount name, discount, original and final prices");
        String gameWithMinDiscount = indiePage.getNameOfGameWithMinDiscount();
        int discount = indiePage.getMinDiscount();
        double originalPrice = indiePage.getOriginalPriceOfGameWithMinDiscount();
        double finalPrice = indiePage.getFinalPriceOfGameWithMinDiscount();
        step("Click the game with the lowest discount (but discount > 0) on the 1 page of the games list." +
            "Enter correct age on the “Rated content” page if it’s shown", counterSteps++);
        indiePage.getTopSellersTab().navigateTo("MinDiscount");

        if (ConfirmAgeForm.IsDisplayed()) {
            LOGGER.info("Confirm right age");
            Steps.confirmAge();
        }

        GamePage gamePage = new GamePage();
        LOGGER.info("Check selected game page is opened");
        assertTrue(gamePage.getGameName().toLowerCase().trim().contains(gameWithMinDiscount.toLowerCase().trim()), "This is not the chosen game's page");
        step("Check that game discount rate, initial and discounted prices are the same as on the step 4", counterSteps++);
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }
}

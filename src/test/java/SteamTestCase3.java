import Steps.Steps;
import appUtils.Utils;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steamElements.ConfirmAgeForm;
import steamPages.GamePage;
import steamPages.GenrePage;
import steamPages.HomePage;

import static appUtils.LoggerUtil.LOGGER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTestCase3 {

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
        LOGGER.info("Step 1. Open http://store.steampowered.com/");
        Browser.enterUrl(PropertyManager.getProperty("src/main/resources/config.properties", "url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        LOGGER.info("Step 3. Select Games -> Indie in the top menu");
        homePage.getNavigationMenu().clickOnGenreTab();
        homePage.getGenreMenu().navigateTo(Utils.getGenre("Indie"));

        GenrePage indiePage = new GenrePage();
        LOGGER.info("Check 'Browsing Indie' page is opened");
        assertTrue(indiePage.isGenrePage(Utils.getGenre("Indie")), "This is not the indie games page");
        LOGGER.info("Step 3. Navigate to “Top Selling” tab");
        indiePage.getTabBar().navigateToTopSellers();
        LOGGER.info("Check Top Selling tab is opened");
        assertTrue(indiePage.isTopSellersClicked(), "Didn't click on top sellers");
        LOGGER.info("Save the game's with min discount name, discount, original and final prices");
        String gameWithMinDiscount = indiePage.getNameOfGameWithMinDiscount();
        int discount = indiePage.getMinDiscount();
        double originalPrice = indiePage.getOriginalPriceOfGameWithMinDiscount();
        double finalPrice = indiePage.getFinalPriceOfGameWithMinDiscount();
        LOGGER.info("Step 4. Click the game with the lowest discount (but discount > 0) on the 1 page of the games list." +
                "Enter correct age on the “Rated content” page if it’s shown");
        indiePage.getTopSellersTab().navigateTo("MinDiscount");

        if (ConfirmAgeForm.IsDisplayed()) {
            LOGGER.info("Confirm right age");
            Steps.confirmAge();
        }

        GamePage gamePage = new GamePage();
        LOGGER.info("Check selected game page is opened");
        assertTrue(gamePage.getGameName().toLowerCase().trim().contains(gameWithMinDiscount.toLowerCase().trim()), "This is not the chosen game's page");
        LOGGER.info("Step 5. Check that game discount rate, initial and discounted prices are the same as on the step 4");
        assertEquals(discount, gamePage.getDiscount(), "Discounts are not the same");
        assertEquals(originalPrice, gamePage.getOriginPrice(), "Origin prices are not the same");
        assertEquals(finalPrice, gamePage.getFinalPrice(), "Final prices are not the same");
    }
}

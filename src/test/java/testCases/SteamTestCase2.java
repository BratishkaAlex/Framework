package testCases;

import Steps.Steps;
import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.PropertyManager;
import org.testng.annotations.*;
import pageObject.steamPages.CheckAgePage;
import pageObject.steamPages.GamePage;
import pageObject.steamPages.GenrePage;
import pageObject.steamPages.HomePage;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SteamTestCase2 {

    int counterSteps;

    @BeforeTest
    public void setUp() {
        Steps.setUpLogger(this.getClass().getName());
        Steps.setUpBrowser();
    }

    @BeforeMethod
    public void initiateCounter() {
        counterSteps = 1;
    }

    @AfterTest
    public void closeBrowser() {
        Steps.closeBrowser();
    }

    @DataProvider(name = "Search discount")
    public static Object[][] getParams() {
        return new Object[][]{{"Action", "MaxDiscount"}, {"Indie", "MinDiscount"}};
    }


    @Test(dataProvider = "Search discount")
    public void testCase2(String genre, String discountRate) {
        step("Open http://store.steampowered.com/", counterSteps++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        HomePage homePage = new HomePage();
        LOGGER.info("Check Steam store main page is opened");
        assertTrue(homePage.isHomePage(), "This is not the home page");
        step(String.format("Select Games -> %s in the top menu", genre), counterSteps++);
        homePage.getNavigationMenu().clickOnGenreTab();
        homePage.getGenreMenu().navigateTo(PropertyManager.getWordFromDictionary(genre));

        GenrePage actionPage = new GenrePage();
        LOGGER.info(String.format("Check 'Browsing %s' page is opened", genre));
        assertTrue(actionPage.isGenrePage(PropertyManager.getWordFromDictionary(genre)), String.format("This is not the %s games page", genre));
        step("Navigate to “Top Selling” tab", counterSteps++);
        actionPage.getTabBar().navigateToTopSellers();
        LOGGER.info("Check Top Selling tab is opened");
        assertTrue(actionPage.isTopSellersClicked(), "Didn't click on top sellers");
        LOGGER.info(String.format("Save the game's with %s name, discount, original and final prices", discountRate));
        String gameWithMaxDiscount = actionPage.getName(discountRate);
        int discount = actionPage.getDiscount(discountRate);
        double originalPrice = actionPage.getOriginalPrice(discountRate);
        double finalPrice = actionPage.getFinalPrice(discountRate);
        step(String.format("Click the game with the %s on the 1 page of the games list." +
                "Enter correct age on the “Rated content” page if it’s shown", discountRate), counterSteps++);
        actionPage.getTopSellersTab().navigateTo(discountRate);

        if (CheckAgePage.IsDisplayed()) {
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

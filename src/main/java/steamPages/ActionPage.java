package steamPages;

import SteamElements.Button;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionPage {
    private WebDriver webDriver;
    private By topSpellingLoc = By.id("tab_select_TopSellers");
    private By listDiscountsLoc = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");
    private By listPricesWithoutDiscounts = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']/parent::div//div[@class='discount_original_price']");

    public ActionPage() {
        webDriver = Browser.getDriver();
    }

    private Button getTopSpelling() {
        return new Button(topSpellingLoc);
    }

    public void clickOnTopSpelling() {
        getTopSpelling().click();
    }

    private List<WebElement> getListDiscounts() {
        return webDriver.findElements(listDiscountsLoc);
    }

    private WebElement getGameWithMaxDiscount() {
        ArrayList<Integer> listDiscounts = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        int indexMaxDiscount = 0;
        int maxDiscount = 0;
        for (int i = 0; i < getListDiscounts().size(); i++) {
            String line = getListDiscounts().get(i).getText();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                listDiscounts.add(Integer.valueOf(line.substring(matcher.start(), matcher.end())));
            }
            if (listDiscounts.get(i).intValue() > maxDiscount) {
                maxDiscount = listDiscounts.get(i).intValue();
                indexMaxDiscount = i;
            }
        }
        System.out.println(listDiscounts.get(indexMaxDiscount).intValue());
        return getListDiscounts().get(indexMaxDiscount);
    }

    public void clickOnGame() {
        getGameWithMaxDiscount().click();
    }

}

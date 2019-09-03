package steamPages;

import framework.utils.NumberReader;
import org.openqa.selenium.By;
import steamElements.Div;
import steamElements.Label;
import steamElements.TabBar;
import steamElements.TopSellersTab;

public class GenrePage {
    public TabBar tabBar;
    public TopSellersTab topSellersTab;

    private String pattern = "//h2[@class='pageheader' and contains(text(), '%s')]";
    private By originPriceLoc = By.xpath("parent::div//div[@class='discount_original_price']");
    private By finalPriceLoc = By.xpath("parent::div//div[@class='discount_final_price']");
    private By gameNameLoc = By.xpath("../following-sibling::div//div[contains(@class,'tab_item_name')]");
    private By topSellersTabLoc = By.id("TopSellersTable");

    public GenrePage() {
        tabBar = new TabBar();
        topSellersTab = new TopSellersTab();
    }

    public boolean isGenrePage(String genre) {
        return new Label(By.xpath(String.format(pattern, genre))).isDisplayed();
    }

    public boolean isTopSellersClicked() {
        return new Div(topSellersTabLoc).isDisplayed();
    }

    public int getMaxDiscount() {
        return NumberReader.getIntNumber(topSellersTab.getGameWithMaxDiscount().getText());
    }

    public Double getOriginalPriceOfGameWithMaxDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(originPriceLoc).getText());
    }

    public Double getFinalPriceOfGameWithMaxDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(finalPriceLoc).getText());
    }

    public String getNameOfGameWithMaxDiscount() {
        return topSellersTab.getGameWithMaxDiscount().findElement(gameNameLoc).getText();
    }

    public int getMinDiscount() {
        return NumberReader.getIntNumber(topSellersTab.getGameWithMinDiscount().getText());
    }

    public Double getOriginalPriceOfGameWithMinDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMinDiscount().findElement(originPriceLoc).getText());
    }

    public Double getFinalPriceOfGameWithMinDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMinDiscount().findElement(finalPriceLoc).getText());
    }

    public String getNameOfGameWithMinDiscount() {
        return topSellersTab.getGameWithMinDiscount().findElement(gameNameLoc).getText();
    }


}

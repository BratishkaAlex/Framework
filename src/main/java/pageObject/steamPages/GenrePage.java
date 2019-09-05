package pageObject.steamPages;

import appUtils.Utils;
import framework.elements.Label;
import framework.utils.NumberReader;
import org.openqa.selenium.By;
import pageObject.steamForms.TabBar;
import pageObject.steamForms.TopSellersTab;

public class GenrePage {
    private TabBar tabBar;
    private TopSellersTab topSellersTab;

    private String pattern = "//h2[@class='pageheader' and contains(text(), '%s')]";
    private By originPriceLoc = By.xpath("parent::div//div[@class='discount_original_price']");
    private By finalPriceLoc = By.xpath("parent::div//div[@class='discount_final_price']");
    private By gameNameLoc = By.xpath("../following-sibling::div//div[contains(@class,'tab_item_name')]");
    private By topSellersTabLoc = By.id("TopSellersTable");

    public GenrePage() {
        tabBar = new TabBar();
        topSellersTab = new TopSellersTab();
    }

    public TabBar getTabBar() {
        return tabBar;
    }

    public TopSellersTab getTopSellersTab() {
        return topSellersTab;
    }

    public boolean isGenrePage(String genre) {
        return new Label(By.xpath(String.format(pattern, genre))).isDisplayed();
    }

    public boolean isTopSellersClicked() {
        return Utils.elementIsDisplayed(topSellersTabLoc);
    }

    public String getName(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                return getNameOfGameWithMaxDiscount();
            case "MinDiscount":
                return getNameOfGameWithMinDiscount();
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    public int getDiscount(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                return getMaxDiscount();
            case "MinDiscount":
                return getMinDiscount();
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    public Double getOriginalPrice(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                return getOriginalPriceOfGameWithMaxDiscount();
            case "MinDiscount":
                return getOriginalPriceOfGameWithMinDiscount();
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    public Double getFinalPrice(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                return getFinalPriceOfGameWithMaxDiscount();
            case "MinDiscount":
                return getFinalPriceOfGameWithMinDiscount();
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    private int getMaxDiscount() {
        return NumberReader.getIntNumber(topSellersTab.getGameWithMaxDiscount().getText());
    }

    private Double getOriginalPriceOfGameWithMaxDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(originPriceLoc).getText());
    }

    private Double getFinalPriceOfGameWithMaxDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(finalPriceLoc).getText());
    }

    private String getNameOfGameWithMaxDiscount() {
        return topSellersTab.getGameWithMaxDiscount().findElement(gameNameLoc).getText();
    }

    private int getMinDiscount() {
        return NumberReader.getIntNumber(topSellersTab.getGameWithMinDiscount().getText());
    }

    private Double getOriginalPriceOfGameWithMinDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMinDiscount().findElement(originPriceLoc).getText());
    }

    private Double getFinalPriceOfGameWithMinDiscount() {
        return NumberReader.getDoubleNumber(topSellersTab.getGameWithMinDiscount().findElement(finalPriceLoc).getText());
    }

    private String getNameOfGameWithMinDiscount() {
        return topSellersTab.getGameWithMinDiscount().findElement(gameNameLoc).getText();
    }

}

package steamPages;

import steamElements.Div;
import steamElements.Label;
import steamElements.TabBar;
import steamElements.TopSpellersTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Reader;

public class ActionPage {
    public TabBar tabBar;
    public TopSpellersTab topSellersTab;
    private By listDiscountsLoc = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");
    private By originPriceLoc = By.xpath("parent::div//div[@class='discount_original_price']");
    private By finalPriceLoc = By.xpath("parent::div//div[@class='discount_final_price']");
    private By actionPageLoc = By.xpath("//h2[@class='pageheader' and (contains(text(), 'Action') or contains(text(), 'Экшен'))]");
    private By gameNameLoc = By.xpath("../following-sibling::div//div[contains(@class,'tab_item_name')]");
    private By topSellersTabLoc = By.id("TopSellersTable");

    public ActionPage(){
        tabBar = new TabBar();
        topSellersTab = new TopSpellersTab();
    }

    public boolean isTopSellersClicked() {
        return new Div(topSellersTabLoc).isDisplayed();
    }

//   private List<WebElement> getListDiscounts() {
//        return webDriver.findElements(listDiscountsLoc);
//    }

    private WebElement getGameWithMaxDiscount() {
        int indexMaxDiscount = 0;
        int maxDiscount = 0;
        for (int i = 0; i < Label.getListElements(listDiscountsLoc).size(); i++) {
            String line = Label.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = Reader.getIntNumber(line);
            if (tempDiscount > maxDiscount) {
                maxDiscount = tempDiscount;
                indexMaxDiscount = i;
            }
        }
        return Label.getListElements(listDiscountsLoc).get(indexMaxDiscount);
    }
    public int getDiscount() {
        return Reader.getIntNumber(topSellersTab.getGameWithMaxDiscount().getText());
    }

    public Double getOriginalPrice() {
        return Reader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(originPriceLoc).getText());
    }

    public Double getFinalPrice() {
        return Reader.getDoubleNumber(topSellersTab.getGameWithMaxDiscount().findElement(finalPriceLoc).getText());
    }

    public String getGameName() {
        return topSellersTab.getGameWithMaxDiscount().findElement(gameNameLoc).getText();
    }
    /*
    public int getDiscount() {
        return Reader.getIntNumber(getGameWithMaxDiscount().getText());
    }

    public Double getOriginalPrice() {
        return Reader.getDoubleNumber(getGameWithMaxDiscount().findElement(originPriceLoc).getText());
    }

    public Double getFinalPrice() {
        return Reader.getDoubleNumber(getGameWithMaxDiscount().findElement(finalPriceLoc).getText());
    }

    public String getGameName() {
        return getGameWithMaxDiscount().findElement(gameNameLoc).getText();
    }
*/
    public boolean isActionGamesPage() {
        return new Label(actionPageLoc).isDisplayed();
    }


}

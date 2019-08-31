package steamPages;

import steamElements.Button;
import steamElements.Label;
import steamElements.TopSpellersTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Reader;

public class IndiePage {

    private By indiePageLoc = By.xpath("//h2[@class='pageheader' and (contains(text(), 'Indie') or contains(text(), 'Инди'))]");
    private By topSpellingLoc = By.id("tab_select_TopSellers");
    private By topSpellersTabLoc = By.id("TopSellersTable");
    private By listDiscountsLoc = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");
    private By gameNameLoc = By.xpath("../following-sibling::div//div[contains(@class,'tab_item_name')]");
    private By originPriceLoc = By.xpath("parent::div//div[@class='discount_original_price']");
    private By finalPriceLoc = By.xpath("parent::div//div[@class='discount_final_price']");

    public boolean isActionGamesPage() {
        return new Label(indiePageLoc).isDisplayed();
    }

    private Button getTopSpelling() {
        return new Button(topSpellingLoc);
    }

    public void clickOnTopSpelling() {
        getTopSpelling().click();
    }

    public boolean isTopSpellersClicked() {
        return new TopSpellersTab(topSpellersTabLoc).isDisplayed();
    }

    private WebElement getGameWithMinDiscount() {
        int indexMinDiscount = 0;
        int minDiscount = 100;
        for (int i = 0; i < Label.getListElements(listDiscountsLoc).size(); i++) {
            String line = Label.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = Reader.getIntNumber(line);
            if (tempDiscount < minDiscount) {
                minDiscount = tempDiscount;
                indexMinDiscount = i;
            }
        }
        return Label.getListElements(listDiscountsLoc).get(indexMinDiscount);
    }

    public String getGameName() {
        return getGameWithMinDiscount().findElement(gameNameLoc).getText();
    }

    public int getDiscount() {
        return Reader.getIntNumber(getGameWithMinDiscount().getText());
    }

    public Double getOriginalPrice() {
        return Reader.getDoubleNumber(getGameWithMinDiscount().findElement(originPriceLoc).getText());
    }

    public Double getFinalPrice() {
        return Reader.getDoubleNumber(getGameWithMinDiscount().findElement(finalPriceLoc).getText());
    }

    public void clickOnGame() {
        getGameWithMinDiscount().click();
    }
}

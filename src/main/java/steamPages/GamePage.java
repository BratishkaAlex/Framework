package steamPages;

import steamElements.Label;
import org.openqa.selenium.By;
import framework.utils.NumberReader;

public class GamePage {
    private By gameNameLoc = By.xpath("(//div[@id='game_area_purchase']//h1)[1]");
    private By gameNameReserveLoc = By.xpath("//div[@class='page_content']//h2[@class='pageheader']");
    private By discountLoc = By.xpath("(//div[@class='game_purchase_action']//div[@class='discount_pct'])[1]");
    private By originPriceLoc = By.xpath("(//div[@class='game_purchase_action']//div[@class='discount_original_price'])[1]");
    private By finalPriceLoc = By.xpath("(//div[@class='game_purchase_action']//div[@class='discount_final_price'])[1]");

    public String getGameName() {
        return new Label(gameNameLoc).getText();
    }

    public int getDiscount() {
        return NumberReader.getIntNumber(new Label(discountLoc).getText());
    }

    public Double getOriginPrice() {
        return NumberReader.getDoubleNumber(new Label(originPriceLoc).getText());
    }

    public Double getFinalPrice() {
        return NumberReader.getDoubleNumber(new Label(finalPriceLoc).getText());
    }
}

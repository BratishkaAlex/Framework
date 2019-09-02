package steamElements;

import elements.BaseForm;
import org.openqa.selenium.By;
import utils.Reader;

public class TopSpellersTab extends BaseForm {


    private By listDiscountsLoc = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");

    public Button getGame(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                return getGameWithMaxDiscount();
            case "MinDiscount":
                return getGameWithMinDiscount();
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    private Button getGameWithMaxDiscount() {
        int indexMaxDiscount = 0;
        int maxDiscount = 0;
        for (int i = 0; i < Button.getListElements(listDiscountsLoc).size(); i++) {
            String line = Button.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = Reader.getIntNumber(line);
            if (tempDiscount > maxDiscount) {
                maxDiscount = tempDiscount;
                indexMaxDiscount = i;
            }
        }
        return new Button(Button.getListElements(listDiscountsLoc).get(indexMaxDiscount));
    }

    private Button getGameWithMinDiscount() {
        int indexMinDiscount = 0;
        int minDiscount = 100;
        for (int i = 0; i < Button.getListElements(listDiscountsLoc).size(); i++) {
            String line = Button.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = Reader.getIntNumber(line);
            if (tempDiscount < minDiscount) {
                minDiscount = tempDiscount;
                indexMinDiscount = i;
            }
        }
        return new Button(Button.getListElements(listDiscountsLoc).get(indexMinDiscount));
    }

}

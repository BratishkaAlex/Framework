package steamElements;

import framework.utils.NumberReader;
import org.openqa.selenium.By;

import static framework.utils.LoggerUtil.LOGGER;

public class TopSellersTab {

    private By listDiscountsLoc = By.xpath("//div[@id='TopSellersTable']//div[@class='discount_pct']");

    public void navigateTo(String discountRate) {
        switch (discountRate) {
            case "MaxDiscount":
                LOGGER.info("Search game with max discount");
                getGameWithMaxDiscount().click();
                break;
            case "MinDiscount":
                LOGGER.info("Search game with min discount");
                getGameWithMinDiscount().click();
                break;
            default:
                throw new IllegalArgumentException("Wrong discount rate");
        }
    }

    public Button getGameWithMaxDiscount() {
        int indexMaxDiscount = 0;
        int maxDiscount = 0;
        for (int i = 0; i < Button.getListElements(listDiscountsLoc).size(); i++) {
            String line = Button.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = NumberReader.getIntNumber(line);
            if (tempDiscount > maxDiscount) {
                maxDiscount = tempDiscount;
                indexMaxDiscount = i;
            }
        }
        return new Button(Button.getListElements(listDiscountsLoc).get(indexMaxDiscount), listDiscountsLoc);
    }

    public Button getGameWithMinDiscount() {
        int indexMinDiscount = 0;
        int minDiscount = 100;
        for (int i = 0; i < Button.getListElements(listDiscountsLoc).size(); i++) {
            String line = Button.getListElements(listDiscountsLoc).get(i).getText();
            int tempDiscount = NumberReader.getIntNumber(line);
            if (tempDiscount < minDiscount) {
                minDiscount = tempDiscount;
                indexMinDiscount = i;
            }
        }
        return new Button(Button.getListElements(listDiscountsLoc).get(indexMinDiscount), listDiscountsLoc);
    }

}

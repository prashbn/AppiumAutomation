package PageObjects;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    String id_rs_search_src_text = "com.amazon.mShop.android.shopping:id/rs_search_src_text";
    String id_iss_search_suggestions_list_view = "com.amazon.mShop.android.shopping:id/iss_search_suggestions_list_view";
    String text = "What are you looking for?";

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    /**
     * Enter an item in the textField to search
     *
     * @param driver
     * @param itemName
     * @TODO Parametrise once you have everything working
     */
    public HomePage enterLoginUserName(MobileDriver driver, String itemName) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_rs_search_src_text, 30)) {
            driver.findElement(By.id(id_rs_search_src_text)).sendKeys(itemName);
        }
        return this;
    }

    /**
     * Select an item from the list view based on the position
     *
     * @param driver
     * @param position
     * @return
     */
    public HomePage selectItemsFromListView(MobileDriver driver, int position) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_iss_search_suggestions_list_view, 30)) {
            List<WebElement> list = driver.findElements(By.id(id_iss_search_suggestions_list_view));
            list.get(position).click();
        }
        return this;
    }
}

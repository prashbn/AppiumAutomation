package PageObjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class should contain elements and action in Home screen
 * @author prash
 */
public class HomeScreen implements AppiumTestingCore {
    private MobileDriver driver;

    final static Logger logger = Logger.getLogger(HomeScreen.class);

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;
    }

    String id_rs_search_src_text = "com.amazon.mShop.android.shopping:id/rs_search_src_text";
    String id_iss_search_suggestions_list_view = "com.amazon.mShop.android.shopping:id/iss_search_suggestions_list_view";

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    /**
     * Enter an item in the textField to search
     *
     * @param productName
     */
    public HomeScreen enterSearchString(String productName) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_rs_search_src_text, 10)) {
            driver.findElement(By.id(id_rs_search_src_text)).click();
            //for some reason the element is available after a certain amount of time once the click action is performed, wait for the element before sending text
            fluentWaitUtil.isElementDisplayedById(driver, id_rs_search_src_text, 10);
            driver.findElement(By.id(id_rs_search_src_text)).sendKeys(productName);
            ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER);
        }
        return this;
    }

    /**
     * Select an item from the list view based on the position, if no history press ENTER
     *
     * @param selectItem
     * @return
     */
    public HomeScreen selectItemsFromListView(String selectItem) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_iss_search_suggestions_list_view, 10)) {

            List<WebElement> listelements = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text']"));
            if (listelements.isEmpty()) {
                ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER);
                return this;
            }
            if (!listelements.isEmpty()) {
                //This works when you have search history, you can locate elements from search history and click
                try {
                    listelements.forEach(listelement -> {
                        if (selectItem.equals(listelement.getText())) {
                            listelement.click();
                        }
                    });
                } catch (Exception e) {
                    //Need to validate this
                    ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER);
                }
            }
        }
        return this;

    }

}

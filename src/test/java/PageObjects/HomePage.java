package PageObjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.experimental.categories.Categories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage implements AppiumTestingCore {
    private MobileDriver driver;

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
     * @param itemName
     * @TODO Parametrise once you have everything working
     */
    public HomePage enterSearchString(String itemName) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_rs_search_src_text, 30)) {
            driver.findElement(By.id(id_rs_search_src_text)).click();
            //for some reason the element is available after a certain amount of time once the click action is performed, wait for the element before sending text
            fluentWaitUtil.isElementDisplayedById(driver, id_rs_search_src_text, 30);
            ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER);
         /*   Map<String, String> map = new HashMap();
            map.put("action", "done");
            ((AndroidDriver) driver).executeScript("mobile:performEditorAction", map);*/
        }
        return this;
    }

    /**
     * Select an item from the list view based on the position
     *
     * @param selectItem
     * @return
     */
    public HomePage selectItemsFromListView(String selectItem) {
        if (fluentWaitUtil.isElementDisplayedById(driver, id_iss_search_suggestions_list_view, 30)) {

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

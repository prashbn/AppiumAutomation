package PageObjects;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action only from top menu bar
 * @author prash
 */
public class TopMenuActionItems implements AppiumTestingCore {

    private MobileDriver driver;

    //Shopping cart icon
    String id_action_bar_cart_image = "com.amazon.mShop.android.shopping:id/action_bar_cart_image";
    //Also Known as Main Menu with three lines
    String id_chrome_action_bar_burger_icon = "com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon";

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    /**
     * Click on the Cart Icon on the Menu Bar
     *
     * @return
     */
    public TopMenuActionItems openCartItems() {
        driver.findElement(By.id(id_action_bar_cart_image)).click();
        return this;
    }

    public TopMenuActionItems openMainMenu() {
        driver.findElement(By.id(id_chrome_action_bar_burger_icon)).click();
        return this;

    }
}



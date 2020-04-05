package PageObjects;

import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action only from top menu bar
 *
 * @author prash
 */
public class TopMenuActionItems implements AppiumTestingCore {

    final static Logger logger = Logger.getLogger(TopMenuActionItems.class);

    private MobileDriver driver;

    //Shopping cart icon
    String id_action_bar_cart_image = "com.amazon.mShop.android.shopping:id/action_bar_cart_image";
    //Also Known as Main Menu with three lines
    String resourceId_chrome_action_bar_burger_icon_xpath = "//android.widget.ImageView[@content-desc=\"Navigation panel, button, double tap to open side panel\"]";


    String resourceId_Home = "com.amazon.mShop.android.shopping:id/drawer_item_title";

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

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

        if (fluentWaitUtil.isElementDisplayedById(driver, id_action_bar_cart_image, 10)) {
            driver.findElement(By.id(id_action_bar_cart_image)).click();
        }
        return this;
    }

    public TopMenuActionItems openMainMenu() {
        driver.findElement(By.xpath(resourceId_chrome_action_bar_burger_icon_xpath)).click();
        return this;

    }

    public TopMenuActionItems goToHomeScreen() {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.widget.TextView[@text='Home']", 10)) {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).click();
        }
        return this;
    }
}



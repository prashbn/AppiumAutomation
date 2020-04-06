package PageObjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action in checkout page
 *
 * @author prash
 */
public class CheckOutAndCartPage implements AppiumTestingCore {

    private MobileDriver driver;

    private Boolean itemsInCart = true;

    final static Logger logger = Logger.getLogger(CheckOutAndCartPage.class);

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    /**
     * Click to proceed to checkout
     *
     * @return
     */
    public CheckOutAndCartPage clickProceedToCheckout() {
        driver.findElement(By.xpath("//android.widget.Button[@text='Proceed to Checkout']")).click();
        return this;
    }

    /**
     * Delete all items in cart
     *
     * @return
     */
    public CheckOutAndCartPage clearCartItems() {
        if (!checkIfCartEmpty()) {
            while (itemsInCart) {
                logger.info("Deleting elements from cart");
                swipeFindsElementByText("Delete");
            }
        }
        return this;
    }

    /**
     * Search an element based on text
     *
     * @param searchText
     */
    private MobileElement swipeFindsElementByText(String searchText) {
        MobileElement element = null;
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + searchText + "\").instance(0))")).click();
            //Avoid additional scrolling, implementing just scroll to text has some issue as it clicks on a random product from the cart items some times

            if (driver.findElement(By.xpath("//android.widget.Button[@text='Delete']")).isDisplayed()) {
                itemsInCart = true;
            }

        } catch (Exception e) {
            itemsInCart = false;
        }
        return element;
    }

    /**
     * Check if the cart is empty, based on text before swiping and wasting time
     */
    private Boolean checkIfCartEmpty() {
        logger.info("Checking if cart Empty");
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.view.View[@text='Your Amazon cart is empty']", 10)) {
            return driver.findElement(By.xpath("//android.view.View[@text='Your Amazon cart is empty']")).isDisplayed();
        }
        return false;
    }
}

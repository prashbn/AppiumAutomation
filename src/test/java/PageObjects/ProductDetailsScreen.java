package PageObjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action in product detail screen
 * @author prash
 */
public class ProductDetailsScreen implements AppiumTestingCore {

    private MobileDriver driver;

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    String seeAllBuyingOptionXpath = "//android.widget.Button[@text='See All Buying Options']";

    final static Logger logger = Logger.getLogger(ProductDetailsScreen.class);

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    public ProductDetailsScreen addToCart() {
        logger.info("Searching for Buying Option");
        if (!selectFromTheBuyingOption()) {
            logger.info("Searching for Add to Cart");
            swipeTolocateByText("Add to Cart").click();
        }
        return this;
    }

    /**
     * Check if there are buying options. if yes, select and proceed to add to cart
     * TODO there could be more than one buying option, currently for this exercise this is not considered.
     *
     * @return
     */
    private Boolean selectFromTheBuyingOption() {
        try {

            if (fluentWaitUtil.isElementDisplayedByXpath(driver, seeAllBuyingOptionXpath, 10)) {
                driver.findElement(By.xpath(seeAllBuyingOptionXpath)).click();
                swipeTolocateByText("Add to Cart").click();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Swipe the first visible view and try to locate the element based on text
     *
     * @param searchText
     * @return
     */
    private MobileElement swipeTolocateByText(String searchText) {

        return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + searchText + "\").instance(0))"));

    }
}

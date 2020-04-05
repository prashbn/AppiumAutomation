package PageObjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * This class should contain elements and action in product detail screen
 *
 * @author prash
 */
public class ProductDetailsScreen implements AppiumTestingCore {

    final static Logger logger = Logger.getLogger(ProductDetailsScreen.class);

    private MobileDriver driver;

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    String seeAllBuyingOptionXpath = "//android.widget.Button[@text='See All Buying Options']";

    String productDescriptionXpath ="//android.view.View[@resource-id='title_feature_div']/android.view.View[@index='1']";

    String productPriceXpath ="//android.view.View[@resource-id='priceblock_ourprice']";

    HashMap<String, String> selectedProductDetail = new HashMap<>();

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    public ProductDetailsScreen addToCart() {
        logger.info("Searching for Buying Option");
        if (!selectFromTheBuyingOption()) {
            logger.info("Searching for Add to Cart");
            swipeToLocateByText("Add to Cart").click();
        }
        return this;
    }

    /**
     * Method to find product price on product details screen, price doesn't appear to be displayed when the buying option is displayed. reattempt in buying option screen.
     * @return
     */
    public ProductDetailsScreen findProductPrice() {
        //if there are buying option, price won't be displayed.
      try{
            setProductPrice(driver.findElement(By.xpath(productPriceXpath)).getText());
        }
      catch (Exception e){
          logger.info("Price not found on this screen, will reattempt when adding to cart, possible location in buy option.");
      }
        return this;
    }

    public ProductDetailsScreen findProductDescription() {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, seeAllBuyingOptionXpath, 10)) {
            setProductDescription(driver.findElement(By.xpath(productDescriptionXpath)).getText());
        }
        return this;
    }

    private void setProductPrice(String productPrice) {

        if (selectedProductDetail.containsKey("productPrice")) {
            selectedProductDetail.replace("productPrice", productPrice);
        } else {
            selectedProductDetail.put("productPrice", productPrice);
        }
    }

    private void setProductDescription(String productDescription) {

        if (selectedProductDetail.containsKey("productDescription")) {
            selectedProductDetail.replace("productDescription", productDescription);
        } else {
            selectedProductDetail.put("productDescription", productDescription);
        }
    }

    public String getProductDescription(String productDescription) {

        if (!selectedProductDetail.isEmpty()) {

            return (selectedProductDetail.containsKey(productDescription)) ? selectedProductDetail.get(productDescription) : "NO DESCRIPTION FOUND";

        }
        return "NO PRICE FOUND";
    }

    public String getProductPrice(String productPrice) {

        if (!selectedProductDetail.isEmpty()) {

            return (selectedProductDetail.containsKey(productPrice)) ? selectedProductDetail.get(productPrice) : "NO PRICE FOUND";

        }
        return "NO PRICE FOUND";

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
                findProductPriceInBuyingOption();
                swipeToLocateByText("Add to Cart").click();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * This works only when one buying option is available, multiple option mean need to store corresponding $ value of the selected option.
     * @TODO update this method to work with multiple buying option when required.
     */
    public void findProductPriceInBuyingOption() {
        //Regular expressing for $amount E.g $1,200.00
        String regex = "^(\\$|)([1-9]+\\d{0,2}(\\,\\d{3})*|([1-9]+\\d*))(\\.\\d{2})?$";
        List<WebElement> textElementsNotEmpty = driver.findElements(By.xpath("//*[@text!='']"));
        for (WebElement element : textElementsNotEmpty) {
            if (element.getText().matches(regex)) {
                setProductPrice(element.getText());
                break;
            }
        }
        //test using this method likely to fail.
        setProductPrice("NO PRICE FOUND");
    }

    /**
     * Swipe the first visible view and try to locate the element based on text
     *
     * @param searchText
     * @return
     */
    private MobileElement swipeToLocateByText(String searchText) {

        return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + searchText + "\").instance(0))"));

    }
}

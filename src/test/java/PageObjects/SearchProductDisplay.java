package PageObjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class should contain elements and action in search product
 *
 * @author prash
 */
public class SearchProductDisplay implements AppiumTestingCore {

    final static Logger logger = Logger.getLogger(SearchProductDisplay.class);

    private MobileDriver driver;

    String searchResultView_xPath = "//android.view.View[@resource-id='a-page']";

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;
    }

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    String selectedProductionDescription;

    /**
     * Select an item from the based on the text and occurrences
     *
     * @param textToSearch
     * @param occurrences
     * @return
     */
    public SearchProductDisplay selectProductFromList(String textToSearch, int occurrences) {

        if (fluentWaitUtil.isElementDisplayedByXpath(driver, searchResultView_xPath, 10)) {
            MobileElement element = swipeFindsElementByResourceIdAndText("search", textToSearch, occurrences);
            selectedProductionDescription = element.getText();
            element.click();
        }
        return this;
    }

    /**
     * TODO Enable swipe with a specific resource and search text
     *
     * @param filterText
     * @return
     */
    public SearchProductDisplay enableProductFilter(String filterText) {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.view.View[@resource-id='a-page']", 10)) {
            driver.findElement(By.xpath("//android.view.View[@resource-id='s-all-filters']")).click();
            //TODO filter-s-all-filters why can't i swipe inside this view with this resource which is more solid than the below search just by text.
            swipeToLocateByText("Department", "1");
            driver.findElement(By.xpath("//android.view.View[@text='" + filterText + "']")).click();
        }
        return this;
    }


    /**
     * Returns selected product.
     * TODO Implement HashMap as more than one element will be added if required, not required for this exercise
     *
     * @return
     */
    public String getSelectedProductDescriptionText() {
        return selectedProductionDescription;
    }

    /**
     * TODO Implement HashMap as more than one element will be added if required, not required for this exercise
     * set select product description used for validation in checkout page
     */
    public void setSelectedProductDescriptionText(String selectedProductionDescription) {
        this.selectedProductionDescription = selectedProductionDescription;

    }


    /**
     * Search a element based on resource and text and occurrences
     *
     * @param resource
     * @param searchText
     */
    private MobileElement swipeFindsElementByResourceIdAndText(String resource, String searchText, int occurrences) {
        MobileElement element = null;
        try {
            element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\"" + resource + "\")).scrollIntoView("
                            + "new UiSelector().textContains(\"" + searchText + "\").instance(" + occurrences + "))"));

        } catch (Exception e) {

        }
        return element;
    }


    private MobileElement swipeToLocateByText(String searchText, String viewInstance) {

        return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(" + viewInstance + ")).scrollIntoView(new UiSelector().textContains(\"" + searchText + "\").instance(0))"));

    }

    /**
     * Method to swipe vertically.
     * TODO move this to util class, not on priority.
     */
    public SearchProductDisplay swipeRandom() {
        Dimension size = driver.manage().window().getSize();
        int width = (int) (size.width / 2);
        int startPoint = (int) (size.getHeight() * 0.70);
        int endPoint = (int) (size.getHeight() * 0.20);
        int duration = 2000;

        int value = ThreadLocalRandom.current().nextInt(2, 6);
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.view.View[@resource-id='a-page']", 10)) {
            logger.info("Performing random swipe : " + value);
            for (int i = 0; i < value; i++) {
                driver.swipe(width, startPoint, width, endPoint, duration);
            }
        }
        return this;
    }

    /**
     * Select a random product from the screen, the methods searches for views which are clickable, used the text from the view to select the product
     *
     * @return
     */
    public SearchProductDisplay selectARandomProduct() {
        List<WebElement> allViewsInTheScreen = driver.findElements(By.xpath(".//android.view.View[@clickable ='true']"));
        int selectRandomElement = ThreadLocalRandom.current().nextInt(0, allViewsInTheScreen.size());
        setSelectedProductDescriptionText(allViewsInTheScreen.get(selectRandomElement).getText());
        logger.info("Select random product with text : " + allViewsInTheScreen.get(selectRandomElement).getText());
        driver.findElement(By.xpath(".//android.view.View[@text='" + allViewsInTheScreen.get(selectRandomElement).getText() + "']")).click();
        return this;
    }
}


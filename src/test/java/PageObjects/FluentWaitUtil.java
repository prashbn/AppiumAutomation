package PageObjects;

import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FluentWaitUtil {

    final static Logger logger = Logger.getLogger(FluentWaitUtil.class);

    /**
     * Returns a boolean value, when elements is located.
     *
     * @param driver
     * @param locator
     * @param time
     * @return
     */
    public boolean isElementDisplayedById(MobileDriver driver, String locator, int time) {
        boolean status = true;
        try {
            logger.info("Checking the " + locator + "  having " + time + " seconds limit");
            fluentWait(driver, locator, time).until(ExpectedConditions.elementToBeClickable(By.id(locator)));
        } catch (TimeoutException e) {
            status = false;
        }
        return status;
    }

    /**
     * Returns a boolean value, when elements is located by XPATH.
     *
     * @param driver
     * @param locator
     * @param time
     * @return
     */
    public boolean isElementDisplayedByXpath(MobileDriver driver, String locator, int time) {
        boolean status = true;
        try {
            logger.info("Checking the " + locator + "  having " + time + " seconds limit");
            fluentWait(driver, locator, time).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        } catch (TimeoutException e) {
            status = false;
        }
        return status;
    }

    /**
     * Returns a boolean value, when elements is located by LinkText.
     *
     * @param driver
     * @param locator
     * @param time
     * @return
     */
    public boolean isElementDisplayedByText(MobileDriver driver, String locator, int time) {
        boolean status = true;
        try {
            logger.info("Checking the " + locator + "  having " + time + " seconds limit");
            fluentWait(driver, locator, time).until(ExpectedConditions.elementToBeClickable(By.linkText(locator)));
        } catch (TimeoutException e) {
            status = false;
        }
        return status;
    }

    /**
     * Poll for elements to be present in the page
     *
     * @param driver
     * @param locator
     * @param time
     * @return
     */
    private FluentWait<MobileDriver> fluentWait(MobileDriver driver, String locator, int time) {
        return new FluentWait<MobileDriver>(driver)
                .withTimeout(time, SECONDS)
                .pollingEvery(5, SECONDS) //Set query/check/control interval
                .ignoring(NoSuchElementException.class)
                .withMessage(locator + "Element Not Present");
    }
}

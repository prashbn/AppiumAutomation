package PageObjects;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SearchProductDisplay implements AppiumTestingCore {

    private MobileDriver driver;

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver=driver;

    }

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    /**
     * Select an item from the list view based on the position
     *
     * @return
     */
    public SearchProductDisplay selectItemsFromListView() {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver,"//android.view.View[@resource-id='a-page']", 30)) {
            driver.findElement(By.xpath("//android.view.View[@resource-id='s-all-filters']")).click();
            WebElement tvScreenSize = driver.findElement(By.xpath("//android.view.View[@text='60-69 in']")); //Create a web element of the item you want to scroll to
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tvScreenSize);
            driver.findElement(By.xpath("//android.view.View[@text='60-69 in']")).click();
        }
        return this;

    }
}

package PageObjects;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action in checkout page
 * @author prash
 */
public class CheckOutPage implements AppiumTestingCore {

    private MobileDriver driver;

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    public CheckOutPage clickProceedToCheckout(){
        driver.findElement(By.xpath("//android.widget.Button[@text='Proceed to Checkout']")).click();
        return this;
    }
}

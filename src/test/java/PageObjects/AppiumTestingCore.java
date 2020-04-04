package PageObjects;

import io.appium.java_client.MobileDriver;

/**
 * Interface to be implemented by all PageObjected model class.
 * @author prash
 */
public interface AppiumTestingCore {

    void initializeDriver(MobileDriver driver);
}

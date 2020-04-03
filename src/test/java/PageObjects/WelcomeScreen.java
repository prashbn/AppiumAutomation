package PageObjects;

import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class WelcomeScreen implements AppiumTestingCore {
    private MobileDriver driver;

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;
    }


    final static Logger logger = Logger.getLogger(WelcomeScreen.class);

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    String id_sso_continue = "com.amazon.mShop.android.shopping:id/sso_continue";
    String id_sso_use_different_account = "com.amazon.mShop.android.shopping:id/sso_use_different_account";

    /**
     * Method to verify if the current screen is welcome screen
     *
     * @return
     * @TODO Verify later if there are different ways to locate the current screen
     */
    public Boolean verifyIfInWelcomeScreen() {
        return (fluentWaitUtil.isElementDisplayedById(driver, id_sso_use_different_account, 10));
    }

    /**
     * Continue as current logged user, return WelcomeScreen object for cascade style programming
     *
     * @return
     */
    public WelcomeScreen clickContinueForLoggedUser() {
        driver.findElement(By.id(id_sso_continue)).click();
        return this;
    }

    /**
     * To login as a different user, return WelcomeScreen object for cascade style programming
     *
     * @return
     */
    public WelcomeScreen clickToUseADifferentUser() {
        driver.findElement(By.id(id_sso_use_different_account)).click();
        return this;
    }


}

package PageObjects;

import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class WelcomeScreen {

    final static Logger logger = Logger.getLogger(WelcomeScreen.class);

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    String id_sso_continue = "com.amazon.mShop.android.shopping:id/sso_continue";
    String id_sso_use_different_account = "com.amazon.mShop.android.shopping:id/sso_use_different_account";

    /**
     * Method to verify if the current screen is welcome screen
     *
     * @param driver
     * @return
     * @TODO Verify later if there are different ways to locate the current screen
     */
    public Boolean verifyIfInWelcomeScreen(MobileDriver driver) {
        return (fluentWaitUtil.isElementDisplayedById(driver, id_sso_continue, 30) && fluentWaitUtil.isElementDisplayedById(driver, id_sso_use_different_account, 30));
    }

    /**
     * Continue as current logged user, return WelcomeScreen object for cascade style programming
     *
     * @param driver
     * @return
     */
    public WelcomeScreen clickContinueForLoggedUser(MobileDriver driver) {
        driver.findElement(By.id(id_sso_continue)).click();
        return this;
    }

    /**
     * To login as a different user, return WelcomeScreen object for cascade style programming
     *
     * @param driver
     * @return
     */
    public WelcomeScreen clickCToUseADifferentUser(MobileDriver driver) {
        driver.findElement(By.id(id_sso_use_different_account)).click();
        return this;
    }

}

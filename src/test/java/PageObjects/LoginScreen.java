package PageObjects;

/*import ReusableComponents.ObjectActions;
import org.openqa.selenium.WebElement;*/

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;

public class LoginScreen {

    //String id_ap_email_login_icon = "ap_email_login_icon";
    String id_ap_email_login_txtBox = "ap_email_login";
    String xpath_ap_email_login_txtBox = "//android.widget.EditText[@index='0']";
    String id_continue_button = "continue";
    String add_account = "//android.widget.Button[@content-desc=\"Add Account\"]";

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    /**
     * Method to verify if the current screen is welcome screen
     *
     * @param driver
     * @return
     * @TODO Verify later if there are different ways to locate the current screen
     */
    public Boolean verifyIfInWLoginScreen(MobileDriver driver) {
        return (fluentWaitUtil.isElementDisplayedByXpath(driver, xpath_ap_email_login_txtBox, 30));
    }

    /**
     * Clear any preexisting text
     *
     * @param driver
     */
    public LoginScreen clearAnyPreExistingText(MobileDriver driver) {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, xpath_ap_email_login_txtBox, 30)) {
            driver.findElement(By.id(xpath_ap_email_login_txtBox)).clear();
        }
        return this;
    }

    /**
     * Enter a valid user login name / mobile number
     *
     * @param driver
     * @param userName
     * @TODO Parametrise once you have everything working
     */
    public LoginScreen enterLoginUserName(MobileDriver driver, String userName) {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, xpath_ap_email_login_txtBox, 30)) {
            driver.findElement(By.xpath(xpath_ap_email_login_txtBox)).sendKeys(userName);
        }
        return this;
    }

    /**
     * Click Continue
     *
     * @param driver
     */
    public LoginScreen clickContinue(MobileDriver driver) {
        driver.findElement(By.id(id_continue_button)).click();
        return this;
    }

    /**
     * Method toadd a new user
     * @param driver
     * @return
     */
    public LoginScreen clickAddAccount(MobileDriver driver) {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.widget.Button[@content-desc=\"Add Account\"]", 30)) {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Add Account\"]")).click();
        }
        return this;

    }


}
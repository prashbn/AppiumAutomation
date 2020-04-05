package PageObjects;

import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * This class should contain elements and action in Login screen
 * @author prash
 */
public class LoginScreen implements AppiumTestingCore {

    private MobileDriver driver;

    final static Logger logger = Logger.getLogger(LoginScreen.class);

    @Override
    public void initializeDriver(MobileDriver driver) {
        this.driver = driver;

    }

    String resource_id_ap_email_login_txtBox_xPath = "//android.widget.EditText[@resource-id='ap_email_login']";
    String resource_id_continue_button_xpath = "//android.widget.Button[@resource-id='continue']";
    String resource_id_ap_login_password_txtBox_xPath = "//android.widget.EditText[@resource-id='ap_password']";
    String resource_id_sigin_button_xpath = "//android.widget.Button[@resource-id='signInSubmit']";

    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();

    /**
     * Method to verify if the current screen is welcome screen
     *
     * @return
     * @TODO Verify later if there are different ways to locate the current screen
     */
    public Boolean verifyIfInWLoginScreen() {
        return (fluentWaitUtil.isElementDisplayedByXpath(driver, resource_id_ap_email_login_txtBox_xPath, 10));
    }

    /**
     * Method to verify if the current screen is password
     *
     * @return
     * @TODO Verify later if there are different ways to locate the current screen
     */
    public Boolean verifyIfInPasswordScreen() {
        return (fluentWaitUtil.isElementDisplayedByXpath(driver, resource_id_ap_login_password_txtBox_xPath, 10));
    }

    /**
     * Clear any preexisting text
     */
    public LoginScreen clearAnyPreExistingText() {
        driver.findElement(By.xpath(resource_id_ap_email_login_txtBox_xPath)).clear();
        return this;
    }

    /**
     * Enter a valid user login name / mobile number
     *
     * @param userName
     * @TODO Parametrise once you have everything working
     */
    public LoginScreen enterLoginUserName( String userName) {
        driver.findElement(By.xpath(resource_id_ap_email_login_txtBox_xPath)).sendKeys(userName);
        return this;
    }

    /**
     * Enter a password
     *
     * @param password
     * @TODO Parametrise once you have everything working
     */
    public LoginScreen enterUserPassword(String password) {
        if (verifyIfInPasswordScreen()) {
            driver.findElement(By.xpath(resource_id_ap_login_password_txtBox_xPath)).sendKeys(password);
        }
        return this;
    }


    /**
     * Method to select ADD Account
     *
     * @return
     */
    public LoginScreen clickAddAccount() {
        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.widget.Button[@content-desc=\"Add Account\"]", 10)) {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Add Account\"]")).click();
        }
        return this;

    }

    /**
     * Click Continue in Enter User Name login screen
     */
    public LoginScreen clickContinue() {
        driver.findElement(By.xpath(resource_id_continue_button_xpath)).click();
        return this;
    }

    /**
     * Click sign-in Enter password screen
     */
    public LoginScreen clickSignIn() {
        driver.findElement(By.xpath(resource_id_sigin_button_xpath)).click();
        return this;
    }


}

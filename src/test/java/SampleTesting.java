import PageObjects.LoginScreen;
import PageObjects.WelcomeScreen;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@RunWith(JUnit4.class)
public class SampleTesting {

    static MobileDriver driver;
    static WebDriver webDriver;

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    LoginScreen loginScreen = new LoginScreen();


    @BeforeClass
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
        capabilities.setCapability("newCommandTimeout", "6000");

        //The amazon app uses web-view container
        capabilities.setCapability("autoWebView", true);
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\prash\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        webDriver = (WebDriver) driver;
    }

    @Test
    public void firstStep() throws Exception {
        //Verify if the elements we are expecting in welcome screen in present
        Assert.assertTrue(welcomeScreen.verifyIfInWelcomeScreen(driver));
        //Uncomment the existing step if you want to continue with existing logged in user
        //welcomeScreen.clickContinue(driver);
        welcomeScreen.clickCToUseADifferentUser(driver);
        //uncomment this if you have downloaded it from app store
        //loginScreen.clickAddAccount(driver);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        driver.context(contextNames.toArray()[1].toString());
        Assert.assertTrue(loginScreen.verifyIfInWLoginScreen(driver));
        //No assertion required if exist clear the text box
        loginScreen.clearAnyPreExistingText(driver);
        //From assert statement above we knew this userName object exists or not, no need to reassert it
        loginScreen.enterLoginUserName(driver, "prashbn@gmail.com").clickContinue(driver);
    }
}

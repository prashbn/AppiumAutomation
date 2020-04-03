import PageObjects.HomePage;
import PageObjects.LoginScreen;
import PageObjects.SearchProductDisplay;
import PageObjects.WelcomeScreen;
import io.appium.java_client.MobileDriver;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.MalformedURLException;

@RunWith(JUnit4.class)
public class SampleTesting extends AppiumDriverSetupForTest {

    static MobileDriver driver;

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    LoginScreen loginScreen = new LoginScreen();
    SearchProductDisplay searchProductDisplay = new SearchProductDisplay();
    HomePage homePage = new HomePage();


    @BeforeClass
    public static void setUpTest() throws MalformedURLException {
        AppiumDriverSetupForTest appiumDriverSetupForTest = new AppiumDriverSetupForTest();
        driver = appiumDriverSetupForTest.setUp();
    }

    @Before
    /**
     * @TODO Should autowire things rather than creating objects, need to sprinfigy, can I?
     */
    public void initializeDriverInOtherClasses() {
        welcomeScreen.initializeDriver(driver);
        loginScreen.initializeDriver(driver);
        searchProductDisplay.initializeDriver(driver);
        homePage.initializeDriver(driver);
    }

    @Test
    public void firstStep() throws Exception {
        String searchItemName = "65-Inch TV";

        //Verify if the elements we are expecting in welcome screen in present
        Assert.assertTrue(welcomeScreen.verifyIfInWelcomeScreen());
        //Uncomment the existing step if you want to continue with existing logged in user
        //welcomeScreen.clickContinueForLoggedUser(driver);
        welcomeScreen.clickToUseADifferentUser();
        //uncomment this if you have downloaded it from app store
        loginScreen.clickAddAccount();
        Assert.assertTrue(loginScreen.verifyIfInWLoginScreen());
        loginScreen.clearAnyPreExistingText().enterLoginUserName("prashbn@gmail.com").clickContinue().enterUserPassword("Enabled14$").clickSignIn();
        //From assert statement above we knew this userName object exists or not, no need to reassert it
        homePage.enterSearchString(searchItemName).selectItemsFromListView(searchItemName);
        searchProductDisplay.selectItemsFromListView();

    }

    @After
    public void TearDown() {
        driver.quit();
    }
}

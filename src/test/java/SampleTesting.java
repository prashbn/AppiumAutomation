import PageObjects.*;
import io.appium.java_client.MobileDriver;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

@RunWith(JUnit4.class)
/**
 * This class should contain only test script related methods and all function needs to be in the created page object package.
 * @author prash
 */
public class SampleTesting extends AppiumDriverSetupForTest {

    static MobileDriver driver;

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    LoginScreen loginScreen = new LoginScreen();
    SearchProductDisplay searchProductDisplay = new SearchProductDisplay();
    HomeScreen homePage = new HomeScreen();
    ProductDetailsScreen productDetailsPage = new ProductDetailsScreen();
    TopMenuActionItems topMenuActionItems = new TopMenuActionItems();
    CheckOutPage checkOutPage = new CheckOutPage();


    @BeforeClass
    public static void setUpTest() throws MalformedURLException {
        AppiumDriverSetupForTest appiumDriverSetupForTest = new AppiumDriverSetupForTest();
        driver = appiumDriverSetupForTest.setUp();
    }

    @Before
    /**
     * @TODO Should autowire things rather than creating objects, need to sprinfigy, Need more work and time pff?
     */
    public void initializeDriverInOtherClasses() {
        welcomeScreen.initializeDriver(driver);
        loginScreen.initializeDriver(driver);
        homePage.initializeDriver(driver);
        searchProductDisplay.initializeDriver(driver);
        productDetailsPage.initializeDriver(driver);
        checkOutPage.initializeDriver(driver);
        topMenuActionItems.initializeDriver(driver);
    }

    @Test
    public void firstStep() {
        String searchItemName = "65-Inch TV";
        //Verify if the elements we are expecting in welcome screen in present
        Assert.assertTrue(welcomeScreen.verifyIfInWelcomeScreen());
        //Uncomment the existing step if you want to continue with existing logged in user
        //welcomeScreen.clickContinueForLoggedUser(driver);
        welcomeScreen.clickToUseADifferentUser();
        loginScreen.clickAddAccount();
        Assert.assertTrue(loginScreen.verifyIfInWLoginScreen());
        loginScreen.clearAnyPreExistingText().enterLoginUserName("prashbn@gmail.com").clickContinue().enterUserPassword("NOT MY PASSWORD").clickSignIn();
        //From assert statement above we knew this userName object exists or not, no need to reassert it
        homePage.enterSearchString(searchItemName).selectItemsFromListView(searchItemName);
        searchProductDisplay.swipeRandom().selectARandomProduct();
        //If a particular product matching the certain description needs to be selected
        //searchProductDisplay.selectProductFromList("SAMSUNG 55", 1);
        productDetailsPage.addToCart();
        topMenuActionItems.openCartItems();
        //This is a crappy, it doesn't work, last thing to figure it out by tomorrow

        Assert.assertTrue(searchProductDisplay.getSelectedProductDescriptionText().contains(driver.findElement(By.xpath("//*[contains(text(),‘" + searchProductDisplay.getSelectedProductDescriptionText().substring(0, 7) + "’)]")).getText().replace(".", "")));
        checkOutPage.clickProceedToCheckout();

    }

    @After
    public void TearDown() {
        driver.closeApp();
        driver.quit();
    }
}

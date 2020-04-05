import PageObjects.*;
import io.appium.java_client.MobileDriver;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
/**
 * This class should contain only test script related methods and all function needs to be in the created page object package.
 * @author prash
 */
public class SampleJunitTesting extends AppiumDriverSetupForTest {
    final static Logger logger = Logger.getLogger(SampleJunitTesting.class);

    static MobileDriver driver;

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    LoginScreen loginScreen = new LoginScreen();
    SearchProductDisplay searchProductDisplay = new SearchProductDisplay();
    HomeScreen homePage = new HomeScreen();
    ProductDetailsScreen productDetailsPage = new ProductDetailsScreen();
    TopMenuActionItems topMenuActionItems = new TopMenuActionItems();
    CheckOutAndCartPage checkOutAndCartPage = new CheckOutAndCartPage();
    FluentWaitUtil fluentWaitUtil = new FluentWaitUtil();


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
        checkOutAndCartPage.initializeDriver(driver);
        topMenuActionItems.initializeDriver(driver);
        driver.rotate(ScreenOrientation.PORTRAIT);
        //TODO need to tweak how the elements are located as this would cause issues in landscape mode, not today.
        //Login works, search product works, the filter doesn't work, need to fix. Rest of things looks ok in landscape mode a thorough testing is required.
    }

    @Test
    public void firstStep() {
        String searchItemName = "65-Inch TV";
        String loginName = "prashbn@gmail.com";
        String password = "Enabled14$";
        String filterBy = "TVs";
        //Verify if the elements we are expecting in welcome screen in present
        Assert.assertTrue(welcomeScreen.verifyIfInWelcomeScreen());
        //Uncomment the existing step if you want to continue with existing logged in user
        //welcomeScreen.clickContinueForLoggedUser(driver);
        welcomeScreen.clickToUseADifferentUser();
        loginScreen.clickAddAccount();
        Assert.assertTrue(loginScreen.verifyIfInWLoginScreen());
        loginScreen.clearAnyPreExistingText().enterLoginUserName(loginName).clickContinue().enterUserPassword(password).clickSignIn();
        //Clear previous items in cart
        topMenuActionItems.openCartItems();
        checkOutAndCartPage.clearCartItems();
        topMenuActionItems.openMainMenu().goToHomeScreen();
        //From assert statement above we knew this userName object exists or not, no need to reassert it
        homePage.enterSearchString(searchItemName).selectItemsFromListView(searchItemName);
        searchProductDisplay.enableProductFilter(filterBy).swipeRandom().selectARandomProduct();
        //If a particular product matching the certain description needs to be selected
        //searchProductDisplay.selectProductFromList("SAMSUNG 55", 1);
        productDetailsPage.findProductDescription().findProductPrice().addToCart();
        topMenuActionItems.openCartItems();
        //Verify if the selected product matches in the checkout page matches description and price.
        Assert.assertTrue(validatePrice());
        Assert.assertTrue(validateDescription());
        //Verify if the price and description matches in the result screen
        checkOutAndCartPage.clickProceedToCheckout();
        //Not placing order with my details, this is easy to implement.

    }

    /**
     * TODO this is like searching in the ocean can limit it to just view with text, again it makes no difference, need to implement a proper relative xpath
     *
     * @return
     */
    public Boolean validateDescription() {
        logger.info("Product description from product page" + productDetailsPage.getProductDescription("productDescription"));
        List<WebElement> textElementsNotEmpty = driver.findElements(By.xpath("//*[@text!='']"));

        for (WebElement element : textElementsNotEmpty) {
            logger.info("$ Product description in the screen :" + element.getText());
            if ((productDetailsPage.getProductDescription("productDescription").contains(element.getText().replace("...", "").trim()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     * @TODO Checks total and product price, can implement swipe to text if there are multiple products
     */
    public Boolean validatePrice() {
        List<WebElement> textElementsNotEmpty = new ArrayList<WebElement>();
        logger.info("Price from product page : " + productDetailsPage.getProductPrice("productPrice"));

        if (fluentWaitUtil.isElementDisplayedByXpath(driver, "//android.view.View[@text='" + productDetailsPage.getProductPrice("productPrice").trim() + "']", 20)) {
            textElementsNotEmpty = driver.findElements(By.xpath("//android.view.View[@text='" + productDetailsPage.getProductPrice("productPrice").trim() + "']"));
        }
        if (textElementsNotEmpty.isEmpty())
            return false;
        for (WebElement element : textElementsNotEmpty) {
            logger.info("$ Amount present in the screen " + element.getText());
            if (element.getText().equals(productDetailsPage.getProductPrice("productPrice"))) {
                return true;
            }
        }
        return false;
    }

    @After
    public void TearDown() {
        driver.closeApp();
        driver.quit();
    }
}

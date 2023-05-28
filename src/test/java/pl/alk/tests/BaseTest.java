package pl.alk.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.alk.pop.*;
import pl.alk.utils.Config;
import pl.alk.utils.Core;

import java.net.MalformedURLException;
import java.net.URL;

/***
  Base class for other tests
 */
public class BaseTest extends Core {
    public LoginPage loginPage;
    public MainPage mainPage;
    public CartPage cartPage;
    public ItemsPage itemsPage;
    public PendingItemsPage pendingItemsPage;
    public CustomerZonePage customerZonePage;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "100");
        chromeOptions.setCapability("platformName", "Windows");
// Showing a test name instead of the session id in the Grid UI
        chromeOptions.setCapability("se:name", "My simple test");
// Other type of metadata can be seen in the Grid UI by clicking on the
// session info or via GraphQL
        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
        var grid = new URL("http://localhost:4444/");
        driver = new RemoteWebDriver(grid,chromeOptions);
        //driver = setDriver(Config.BROWSER);
        driver.get("https://demob2b-xl.comarch.pl/");
        //Maximize window
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {


        if (Core.getCartId() != 0) {
            if (this.cartPage!=null){
                cartPage.removeCart(Core.getCartId());
                Core.cartId =0;
            }
        }
        if (Core.isLogged) {
            mainPage.logOut();
            Core.isLogged=false;
        }
        if (!Core.isLogged && Core.getCartId()==0){
            driver.quit();
        }
    }

}

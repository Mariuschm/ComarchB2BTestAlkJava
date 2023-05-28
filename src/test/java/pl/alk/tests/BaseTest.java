package pl.alk.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
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

        switch (Config.BROWSER) {
            case "firefox":
                var firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(Config.REMOTE_DRIVER, firefoxOptions);
                break;
            case "edge":
                var EdgeOptions = new EdgeOptions();
                EdgeOptions.addArguments("--lang=pl-PL");
                EdgeOptions.addArguments("--start-maximized");
                driver = new RemoteWebDriver(Config.REMOTE_DRIVER, EdgeOptions);
                break;
            default:
                var DefOptions = new ChromeOptions();
                DefOptions.addArguments("lang=pl-PL");
                driver = new RemoteWebDriver(Config.REMOTE_DRIVER, DefOptions);
                break;
        }
        // driver = setDriver(Config.BROWSER);
        driver.get("https://demob2b-xl.comarch.pl/");
        //Maximize window
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {


        if (Core.getCartId() != 0) {
            if (this.cartPage != null) {
                cartPage.removeCart(Core.getCartId());
                Core.cartId = 0;
            }
        }
        if (Core.isLogged) {
            mainPage.logOut();
            Core.isLogged = false;
        }
        if (!Core.isLogged && Core.getCartId() == 0) {
            driver.quit();
        }
    }

}

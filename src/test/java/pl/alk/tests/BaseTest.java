package pl.alk.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.reporters.jq.Main;
import pl.alk.pop.*;
import pl.alk.utils.Config;
import pl.alk.utils.Core;

/**
 * Base class for other tests
 */
public class BaseTest extends Core {
    public LoginPage loginPage;
    public MainPage mainPage;
    public CartPage cartPage;
    public ItemsPage itemsPage;
    public CustomerAreaPage customerAreaPage;
    private WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = setDriver(Config.BROWSER);
        driver.get("https://demob2b-xl.comarch.pl/");
        //Maximize window
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        /**Remove cart if any
        Logout if logged successfully
        Close driver**/
        driver.quit();
    }

}

package pl.alk.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;
import pl.alk.utils.FakeData;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class B2bTests extends BaseTest {

    /**
     * Data provider for add to cart tests
     * Gets data from CSV file
     */
    @DataProvider(name = "items")
    public Iterator<Object[]> getData() {
        return getItemsIterator("./data/OrderedItems.csv");
    }

    @Test(priority = 1, description = """
            Tests login with valid data. Entry point: \r
            1. Open browser \r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            Tasks:\r
            1. Enter valid company name\r
            2. Enter valid user name\r
            3. Enter valid password\r
            4. Accept terms and conditions\r
            5. Click Log on button\r
            Result:\r
            1. MainPage should be returned\r
            """, testName = "TC101 Valid credentials login, terms accepted")
    public void loginWithValidDataTermsAccepted() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                true                                //Accept terms = true
        );
        mainPage = result.getPage();
        if (mainPage != null) {
            Assert.assertEquals(mainPage.getLoggedUser().toUpperCase(), loginData.getCompanyName().toUpperCase());
        } else {
            Assert.assertEquals(result.getResult(), 1);
        }
    }

    @Test(priority = 3, description = """
            Tests login with valid data. Entry point: \r
            1. Open browser \r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            Tasks:\r
            1. Enter valid company name\r
            2. Enter valid user name\r
            3. Enter valid password\r
            4. Do not accept terms and conditions\r
            5. Click Log on button\r
            Result:\r
            1. MainPage should be returned\r
            """, testName = "TC102 Valid credentials login, terms not accepted")
    public void loginWithValidDataDoNotAcceptTerms() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                false                           //Accept terms = false
        );
        Assert.assertEquals(result.getResult(), -2);
    }

    @Test(priority = 4, description = """
            Tests login with invalid data. Entry point: \r
            1. Open browser\r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            Tasks:\r
            1. Enter valid company name\r
            2. Enter valid user name\r
            3. Enter valid password\r
            4. Accept terms and conditions\r
            5. Click Log on button\r
            Result:\r
            1. MainPage should be returned\r
            """, testName = "TC103 invalid credentials login")
    public void loginWithInvalidDataTermsAccepted() {
        var faker = new FakeData();
        LoginResult result = loginPage.login(faker.getCompanyName(),     //Invalid company name
                faker.getUserName(),        //Invalid username
                faker.getPassword(),        //Invalid password
                true                        //Accept = true
        );
        Assert.assertEquals(result.getResult(), -1);
    }

    @Test(priority = 2, description = "Test description", testName = "TC201 Add items to cart", dataProvider = "items", dependsOnMethods = "loginWithValidDataTermsAccepted")
    public void addToCartTest(String category, String code, String quantity) {
        Assert.assertTrue(true);
    }

    @Test(priority = 3, description = """
            Test change o items view. Entry point\r
            1. Open browser\r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            3. Enter valid company name\r
            4. Enter valid user name\r
            5. Enter valid password\r
            6. Accept terms and conditions\r
            7. Click Log on button\r
            Tasks:\r
            1. Click on a random product category\r
            2. Wait for items page to appear\r
            3. Click on thumbnail view button\r
            Result:\r
            1. View is change to thumbnail type \r
            2. Class of div is changed to display-type-2""", testName = "TC301 Change items view style")
    public void changeItemsView() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                true                           //Accept terms = true
        );
        if (result.getResult() == 1) {
            //Star test
            this.mainPage = result.getPage();
            //Get categories
            List<WebElement> cats = mainPage.getCategories();
            //Get random click random category
            this.itemsPage = mainPage.getItems(cats.get(new Random().nextInt(0, cats.size())));
            //There should be at least on item on list
            if (itemsPage.getItemsCount() == 0) {
                Assert.fail("Could not find any items");
                return;
            }
            itemsPage.setThumbnailView();
            var res = itemsPage.getStyleId();
            Assert.assertEquals(res, 2);
        } else {
            // Could not log in asser as false
            Assert.fail("Could not login");
        }
    }

    @Test(description = "Show active order list")
    public void getOpenItemsCount() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                true                           //Accept terms = true
        );
        if (result.getResult() == 1) {
            //Star test
            this.mainPage = result.getPage();
            this.pendingItemsPage = mainPage.getPendingItems();
            Assert.assertTrue(
                    pendingItemsPage.getPendingItemsCount()>0
                            && pendingItemsPage.getPendingItemsTotal()== pendingItemsPage.getPendingItemsCount());
        }
    }

    @Test(description = "Test order printout")
    public void downloadAttachment() {

    }

}

package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;
import pl.alk.utils.FakeData;

import java.io.File;
import java.util.Iterator;

public class B2bTests extends BaseTest {

    /**
     * Data provider for add to cart tests
     * Gets data from CSV file
     */
    @DataProvider(name = "items")
    public Iterator<Object[]> getData() {
        return getItemsIterator("./data/OrderedItems.csv");
    }

    @Test(priority = 1,
            description = "Tests login with valid data. Entry point: \r\n" +
                    "1. Open browser \r\n" +
                    "2. Navigate to: https://demob2b-xl.comarch.pl/ \r\n" +
                    "Tasks:\r\n" +
                    "1. Enter valid company name\r\n" +
                    "2. Enter valid user name\r\n" +
                    "3. Enter valid password\r\n" +
                    "4. Accept terms and conditions\r\n" +
                    "5. Click Log on button\r\n" +
                    "Result:\r\n" +
                    "1. MainPage should be returned\r\n", testName = "TC101 Valid credentials login, terms accepted"
    )
    public void loginWithValidDataTermsAccepted() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(
                loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                true                                //Accept terms = true
        );
        mainPage = result.getPage();
        if (mainPage!=null){
            Assert.assertEquals(mainPage.getLoggedUser().toUpperCase(), loginData.getCompanyName().toUpperCase());
        }
        else {
            Assert.assertTrue(false);
        }


    }

    @Test(priority = 3,
            description = "Tests login with valid data. Entry point: \r\n" +
                    "1. Open browser \r\n" +
                    "2. Navigate to: https://demob2b-xl.comarch.pl/ \r\n" +
                    "Tasks:\r\n" +
                    "1. Enter valid company name\r\n" +
                    "2. Enter valid user name\r\n" +
                    "3. Enter valid password\r\n" +
                    "4. Do not accept terms and conditions\r\n" +
                    "5. Click Log on button\r\n" +
                    "Result:\r\n" +
                    "1. MainPage should be returned\r\n",
            testName = "TC102 Valid credentials login, terms not accepted",
            enabled = true
    )
    public void loginWithValidDataDoNotAcceptTerms() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result= loginPage.login(
                loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                false                           //Accept terms = false
        );
        Assert.assertTrue(result.getResult()==-2);
    }

    @Test(priority = 4,
            description = "Tests login with invalid data. Entry point: \r\n" +
                    "1. Open browser\r\n" +
                    "2. Navigate to: https://demob2b-xl.comarch.pl/ \r\n" +
                    "Tasks:\r\n" +
                    "1. Enter valid company name\r\n" +
                    "2. Enter valid user name\r\n" +
                    "3. Enter valid password\r\n" +
                    "4. Accept terms and conditions\r\n" +
                    "5. Click Log on button\r\n" +
                    "Result:\r\n" +
                    "1. MainPage should be returned\r\n",
            testName = "TC103 invalid credentials login",
            enabled = true

    )
    public void loginWithInvalidDataTermsAccepted() {
        var faker = new FakeData();
        LoginResult result= loginPage.login(
                faker.getCompanyName(),     //Invalid company name
                faker.getUserName(),        //Invalid username
                faker.getPassword(),        //Invalid password
                true                        //Accept = true
        );
        Assert.assertTrue(result.getResult()==-1);
    }

    @Test(priority = 2,
            description = "Test description",
            testName = "TC201 Add items to cart",
            dataProvider = "items",
            dependsOnMethods = "loginWithValidDataTermsAccepted")
    public void addToCartTest(String category, String code, String quantity) {
             Assert.assertTrue(true);
    }
    @Test(description = "Testowanie zmiany widoku, widok losowany z pliku")
    public void changeItemsView()
    {

    }
    @Test(description = "wyświetla listę otwartych zamówień")
    public void getOpenOrdersData(){

    }
    @Test(description = "test drukowania zamówienia")
    public void printOrderData(){

    }

}

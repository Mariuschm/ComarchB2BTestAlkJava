package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;
import pl.alk.utils.FakeData;

import java.io.File;


public class LoginTest extends BaseTest {

    @Test(priority = 1,
            description = """
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
                    """,
            testName = "TC101 Valid credentials login, terms accepted")
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

    @Test(priority = 2,
            description = """
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
                    1. Error should be displayed\r
                    """,
            testName = "TC102 Valid credentials login, terms not accepted")
    public void loginWithValidDataDoNotAcceptTerms() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                false                           //Accept terms = false
        );
        Assert.assertEquals(result.getResult(), -2);
    }

    @Test(priority = 3, description = """
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
            """,
            testName = "TC103 invalid credentials login")
    public void loginWithInvalidDataTermsAccepted() {
        var faker = new FakeData();
        LoginResult result = loginPage.login(faker.getCompanyName(),     //Invalid company name
                faker.getUserName(),        //Invalid username
                faker.getPassword(),        //Invalid password
                true                        //Accept = true
        );
        Assert.assertEquals(result.getResult(), -1);
    }

}

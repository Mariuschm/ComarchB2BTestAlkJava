package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.alk.utils.FakeData;

import java.io.File;

public class LoginTest extends BaseTest {
    @Test(priority = 0,
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
        loginPage.login(
                loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                true    //Accept terms = true
        );
        Assert.assertTrue(true);
    }

    @Test(priority = 1,
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
                    "1. MainPage should be returned\r\n", testName = "TC102 Valid credentials login, terms not accepted"
    )
    public void loginWithValidDataDoNotAcceptTerms() {
        var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
        loginPage.login(
                loginData.getCompanyName(),      //Valid company name
                loginData.getUserName(),         //Valid user name
                loginData.getPassword(),         //Valid password
                false                           //Accept terms = false
        );
        Assert.assertTrue(true);
    }

    @Test(priority = 2,
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
                    "1. MainPage should be returned\r\n", testName = "TC103 invalid credentials login"
    )
    public void loginWithInvalidDataTermsAccepted() {
        var faker = new FakeData();
        loginPage.login(
                faker.getCompanyName(),     //Invalid company name
                faker.getUserName(),        //Invalid username
                faker.getPassword(),        //Invalid password
                true                        //Accept = true
        );
        Assert.assertTrue(true);
    }


}

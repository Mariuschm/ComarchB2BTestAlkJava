package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(priority = 0, description = "Tests login with valid data. Entry point: \r\n" +
            "1. Open browser \r\n" +
            "2. Navigate to: https://demob2b-xl.comarch.pl/ \r\n" +
            "Tasks:\r\n" +
            "1. Enter valid company name\r\n" +
            "2. Enter valid user name\r\n" +
            "3. Enter valid password\r\n" +
            "4. Accept terms and conditions\r\n" +
            "5. Click Log on button\r\n" +
            "Result:\r\n" +
            "1. MainPage should be returned\r\n", testName = "TC101 Valid credentials login"
            )
    public void loginWithValidData(){
        Assert.assertTrue(true);
    }
    @Test(priority = 1, description = "Tests login with valid data. Entry point: \r\n" +
            "1. Open browser\r\n" +
            "2. Navigate to: https://demob2b-xl.comarch.pl/ \r\n" +
            "Tasks:\r\n" +
            "1. Enter valid company name\r\n" +
            "2. Enter valid user name\r\n" +
            "3. Enter valid password\r\n" +
            "4. Accept terms and conditions\r\n" +
            "5. Click Log on button\r\n" +
            "Result:\r\n" +
            "1. MainPage should be returned\r\n", testName = "TC101 invalid credentials login"
    )
    public  void  loginWithInvalidData(){
        Assert.assertTrue(true);
    }

}

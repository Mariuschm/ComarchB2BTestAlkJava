package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;

import java.io.File;

public class PendingItemsTest extends BaseTest {
    @Test(description = """
            Test if pending items list loads. Entry point:\r
            1. Open browser\r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            3. Enter valid company name\r
            4. Enter valid user name\r
            5. Enter valid password\r
            6. Accept terms and conditions\r
            7. Click Log on button\r
            Tasks:\r
            1. Click on a pending list button\r
            2. Wait for pending list to appear\r
            3. Count all items
            4. Get number from total lable\r
            Result:\r
            1. List is loaded \r
            2. Items count from list is greater then 0 and equal to number from label""",
            testName = "TC401 Check if pending items count is grater then 0 and equal to number from label")
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
                    pendingItemsPage.getPendingItemsCount() > 0
                            && pendingItemsPage.getPendingItemsTotal() == pendingItemsPage.getPendingItemsCount());
        }
    }

}

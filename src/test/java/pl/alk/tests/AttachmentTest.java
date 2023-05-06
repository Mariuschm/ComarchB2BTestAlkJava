package pl.alk.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class AttachmentTest extends BaseTest{
    @Test(priority = 5, description = """
            Test if pending items list loads. Entry point:\r
            1. Open browser\r
            2. Navigate to: https://demob2b-xl.comarch.pl/ \r
            3. Enter valid company name\r
            4. Enter valid user name\r
            5. Enter valid password\r
            6. Accept terms and conditions\r
            7. Click Log on button\r
            Tasks:\r
            1. Click on a Customer Zone Icon\r
            2. Wait for Customer Zone to open\r
            3. Navigate do Downloads section
            4. Download a file\r
            Result:\r
            1. File is downloaded in lest then 30s (default wait time) \r
            2. File is deleted after test finish""",
            testName = "TC501 Check if a file from downloads section is downloaded in less then 30s")
    public void downloadAttachment() {
        //Test works only on windows
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {


            var loginData = getLoginData(new File(System.getProperty("user.dir")).getAbsolutePath() + "/data/ValidLoginData.csv");
            LoginResult result = loginPage.login(loginData.getCompanyName(),      //Valid company name
                    loginData.getUserName(),         //Valid user name
                    loginData.getPassword(),         //Valid password
                    true                           //Accept terms = true
            );
            if (result.getResult() == 1) {
                //Star test
                this.mainPage = result.getPage();
                this.customerZonePage = mainPage.openCustomerZone();
                //Get list of items
                var fileList = customerZonePage.getFiles();
                //Get random file
                int index = ThreadLocalRandom.current().nextInt(0, fileList.size()-1);
                var file = fileList.get(index);
                Assert.assertTrue(customerZonePage.downloadFile(file));
            }
        } else {
            Assert.fail("Not supported os");
        }
    }
}

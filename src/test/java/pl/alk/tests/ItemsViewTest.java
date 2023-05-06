package pl.alk.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.alk.model.LoginResult;

import java.io.File;
import java.util.List;
import java.util.Random;

public class ItemsViewTest extends BaseTest {
    @Test(description = """
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
            2. Class of div is changed to display-type-2""",
            testName = "TC301 Change items view style")
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
}

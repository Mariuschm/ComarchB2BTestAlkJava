package pl.alk.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.alk.model.CategoryWithName;
import pl.alk.model.ItemWithName;
import pl.alk.model.LoginResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class CartTest extends BaseTest {

    /**
     * Data provider for add to cart tests
     * Gets data from CSV file
     */
    @DataProvider(name = "items")
    public Iterator<Object[]> getData() {
        return getItemsIterator("./data/OrderedItems.csv");
    }

    @Test(description = """
            Tests adding products to cart based on csv file data. After adding product to cart verify if added quantity is equal to
            data from csv file.
            Entry point
            1. Open browser
            2. Navigate to: https://demob2b-xl.comarch.pl/
            3. Enter valid company name
            4. Enter valid user name
            5. Enter valid password
            6. Accept terms and conditions
            7. Click Log on button
            Tasks:
            1. Click on a random product category
            2. Wait for items page to appear
            3. Click on thumbnail view button
            4. Open category provided in csv file
            5. Find item with code from csv file
            6. Set quantity from csv file
            7. Expand cart selection for product
            8. Chose new cart
            9. Add item to cart
            10. Go to the cart
            11. Check if quantity is equal to one provided
            Results:
            1. Cart is added
            2. Item is added to cart
            3. Quantity in cart is equal to one from csv file        
            """,
            testName = "TC201 Add items to cart",
            dataProvider = "items"
            // dependsOnMethods = "LoginTest.loginWithValidDataTermsAccepted"
    )
    public void addToCartTest(String category, String code, String quantity) throws InterruptedException {
        //Login
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
        //Find category
        WebElement locatedCategory;
        try {
            ArrayList<CategoryWithName> categories = mainPage.getCategoriesWithNames();
            locatedCategory = Objects.requireNonNull(categories.stream().filter(name -> category.equals(name.Name)).findAny().orElse(null)).Category;

        } catch (NullPointerException e) {
            Assert.fail("Could not find category");
            return;
        }
        if (locatedCategory == null) {
            Assert.fail("Could not find category");
            return;
        }
        //Change view to Thumbnail
        this.itemsPage = mainPage.getItems(locatedCategory);
        itemsPage.setThumbnailView();
        if (itemsPage.getStyleId() != 2) {
            Assert.fail("Could not change view style to Thumbnail");
            return;
        }
        //Get item with provided name
        WebElement locatedItem;
        try {
            ArrayList<ItemWithName> items = itemsPage.getItemsWithNames();
            locatedItem = Objects.requireNonNull(items.stream().filter(item -> code.equals(item.Name)).findAny().orElse(null)).Item;
        } catch (NullPointerException e) {
            Assert.fail("Could not find item");
            return;
        }
        //Convert quantity to double
        double qt = Double.parseDouble(quantity);
        //Add item to cart and return cart
        this.cartPage = itemsPage.addToCart(locatedItem, qt);
        //Check if quantity in cart is equal to provided
        Assert.assertEquals(qt, cartPage.getOrderedQuantity(code));
    }

}

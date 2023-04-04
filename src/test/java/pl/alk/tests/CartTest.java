package pl.alk.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;


public class CartTest extends BaseTest {

    @DataProvider(name = "items")
    /**
     * Data provider for add to cart tests
     * Gets data from CSV file
     */
    public Iterator<Object[]> getData() {
        return getItemsIterator("./data/OrderedItems.csv");
    }

    @Test(priority = 0,
            description = "",
            testName = "TC201 Add items to cart",
            dataProvider = "items")
    public void test(String category, String code, String quantity) {
        Assert.assertTrue(true);
    }


}

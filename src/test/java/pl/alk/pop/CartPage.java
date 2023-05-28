package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.alk.model.ItemWithName;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

public class CartPage extends BasePage {

    protected By article = By.cssSelector("div[class='trow']");
    protected By articleCode = By.cssSelector("span[class='product-code']");
    protected By orderedQuantity = By.cssSelector("input");
    protected By cartList = By.xpath("//ul[@class='inner-carts-container']//li");
    protected By cartId = By.cssSelector("span[class='name-value']");
    protected By removeCartBtn = By.cssSelector("button");
    protected By confirmRemove = By.xpath("button[1]");
    protected By confirmWindow = By.cssSelector("div[class='modal-content mauto']");
    @FindBy(xpath = "//trigger[@class='user-cart many-currencies']")
    private WebElement cartSelector;
    @FindBy(css = "div[class='articles']")
    private WebElement articles;


    public CartPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(cartSelector));
        wait.until(ExpectedConditions.visibilityOf(articles));
    }

    /***
     * Returns item quantity in cart
     * @param code item code
     * @return quantity as double
     */
    public double getOrderedQuantity(String code) {
        var orderedItems = new ArrayList<ItemWithName>();
        //Get all items with names
        for (WebElement item : articles.findElements(article)
        ) {
            var itemWithName = new ItemWithName();
            itemWithName.Item = item;
            itemWithName.Name = item.findElement(articleCode).getText();
            orderedItems.add(itemWithName);
        }
        WebElement locatedItem;
        try {

            locatedItem = Objects.requireNonNull(orderedItems.stream().filter(
                    item -> code.equals(item.Name)
            ).findAny().orElse(null)).Item;
        } catch (NullPointerException e) {
            Assert.fail("Could not find item");
            return 0;
        }

        return Double.parseDouble(locatedItem.findElement(orderedQuantity).getAttribute("value").replace(",", "."));
    }

    /***
     * Removes cart with provided id
     * @param id cart id
     */
    public void removeCart(int id) {
        //Click on cart selector
        cartSelector.click();
        //Wait for cart list to load
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                .until(ExpectedConditions.visibilityOfElementLocated(cartList));

        for (WebElement cart : driver.findElements(cartList)
        ) {
            if (Integer.parseInt(cart.findElement(cartId).getText()) == id) {
                cart.findElement(removeCartBtn).click();
                new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                        .until(ExpectedConditions.visibilityOfElementLocated(confirmWindow));
                var confirm = driver.findElement(confirmWindow).findElement(confirmRemove);
                confirm.click();
                return;
            }
        }
    }

}

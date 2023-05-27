package pl.alk.pop;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.model.ItemWithName;

import java.time.Duration;
import java.util.ArrayList;

public class ItemsPage extends BasePage {

    @FindBy(css = "i[class='ti-view-grid']")
    protected WebElement setThumbnailViewBtn;
    @FindBy(css = "i[class='ti-view-list-alt']")
    protected WebElement setListViewBtn;
    @FindBy(css = "i[class='ti-view-list']")
    protected WebElement setDetailsViewBtn;
    @FindBy(css = "div[class$='products-list']")
    protected WebElement productsList;
    @FindBy(css = "div[class*='list-container']")
    protected WebElement productsContainer;

    protected By quantityInput = By.cssSelector("input");
    protected By addToCartButton = By.cssSelector("button[class*='add-to-cart']");
    protected By cartSelector = By.xpath("//app-cart-select[@name='cartId']");
    protected By newCartButton = By.id("cartId--1");
    protected By cartConfirmation = By.cssSelector("div[class^='modal-content']");
    protected By cartIdLabel = By.cssSelector("strong");
    protected By goToCart = By.cssSelector("button[class='cart']");
    protected By getProducts = (By.cssSelector("li[class*='list-item']"));
    protected By itemNameLabel = By.cssSelector("p[class*='code']");

    public ItemsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(setThumbnailViewBtn));
        wait.until(ExpectedConditions.visibilityOf(setListViewBtn));
        wait.until(ExpectedConditions.visibilityOf(productsList));
        wait.until(ExpectedConditions.visibilityOf(productsContainer));
    }
    /*Set view style support*/

    /***
     * Sets item view to ThumbnailView
     */
    public void setThumbnailView() {
        setThumbnailViewBtn.click();
    }

    /***
     * Sets item view to ListView
     */
    public void setListView() {
        setListViewBtn.click();
    }

    /***
     * Sets item view to Details
     */
    public void setDetailsVie() {
        setDetailsViewBtn.click();
    }

    /***
     * Returns display-type of current view
     * @return 0 - details view, 1- list view, 2-thumbnail view
     */
    public int getStyleId() {
        var divClass = productsContainer.getAttribute("class");
        var searchString = "display-type-";
        var position = divClass.indexOf(searchString) + searchString.length();
        var ret = divClass.substring(position, position + 1);
        try {
            return Integer.parseInt(ret);
        } catch (NumberFormatException e) {
            return 0;
        } catch (Exception e) {
            return -1;
        }


    }

    /*Get items count*/

    /***
     * Gets items count on product list
     * @return Number of items
     */
    public int getItemsCount() {
        var items = productsList.findElements(getProducts);
        return items.size();
    }

    /***
     * Gets a list of items displayed with their name
     * @return ArrayList of Items with Name
     */
    public ArrayList<ItemWithName> getItemsWithNames() {
        var ret = new ArrayList<ItemWithName>();
        for (WebElement category : productsList.findElements(getProducts)) {
            var elem = new ItemWithName();
            elem.Item = category;
            elem.Name = category.findElement(itemNameLabel).getText();
            ret.add(elem);
        }
        return ret;
    }

    /*Add to cart support*/

    /***
     * Adds selected item to cart
     * @param item one of items from list
     * @param quantity item quantity
     * @return Cart page
     */
    public CartPage addToCart(WebElement item, double quantity) throws InterruptedException {
        //Scroll down to the bottom
        var js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //Get quantity input and pass value
        var qty = item.findElement(quantityInput);
        //Clear data for proper results does not work on autocomplete inputs
        //Works fine when breakpoint set on field input
        qty.clear();
        //Workaround to clear quantity filed as clear() method does not work on autocomplete inputs
        qty.sendKeys(Keys.CONTROL, "A");
        qty.sendKeys(String.valueOf(quantity).replace(".", ","));
        //Chose new cart
        item.findElement(cartSelector).click();
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                .until(ExpectedConditions.visibilityOfElementLocated(newCartButton));
        //Add new cart
        driver.findElement(newCartButton).click();
        //Click add to cart
        item.findElement(addToCartButton).click();
        //Wait for confirmation
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                .until(ExpectedConditions.visibilityOfElementLocated(cartConfirmation));
        //Get new cartId
        this.driver.findElement(cartConfirmation);
        cartId = Integer.parseInt(this.driver.findElement(cartIdLabel).getText());
        //Go to CartPage
        driver.findElement(goToCart).click();
        return new CartPage(driver);
    }
}

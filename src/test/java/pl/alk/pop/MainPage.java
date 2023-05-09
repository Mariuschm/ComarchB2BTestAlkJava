package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.model.CategoryWithName;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    private final By categories = By.xpath("//li[@class='category-item inner-clear']");
    private final By customerZoneLink = By.cssSelector("a");
    protected By categoryNameSelector = By.cssSelector("a[class*='group-label outline button f-left']");
    @FindBy(xpath = "//i[@class='ti-briefcase']/following-sibling::*")
    private WebElement customerName;
    @FindBy(xpath = "//i[@class='ti-lock']/following-sibling::*")
    private WebElement employeeName;
    @FindBy(xpath = "//i[@class='ti-power-off']")
    private WebElement logoutButton;
    @FindBy(css = "i[class$='ti-package']")
    private WebElement pendingItemsBtn;
    @FindBy(css = "div[class='profile-menu-adapter']")
    private WebElement customerZone;

    public MainPage(WebDriver driver) {
        super(driver);
        //Set implicitly defaultWait for 1 s.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Gets logged company label
     *
     * @return logged company
     */
    public String getLoggedUser() {
        return customerName.getText();
    }

    /**
     * Terminates current session
     */
    public void logOut() {
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    /**
     * Returns list o categories on page
     *
     * @return list o web elements
     */
    public List<WebElement> getCategories() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(categories));
        return driver.findElements(categories);
    }

    /**
     * Returns list o categories with names
     *
     * @return ArrayList of CategoryWithName
     */
    public ArrayList<CategoryWithName> getCategoriesWithNames() {
        var ret = new ArrayList<CategoryWithName>();
        for (WebElement category : getCategories()) {
            var elem = new CategoryWithName();
            elem.Category = category;
            elem.Name = category.findElement(categoryNameSelector).getText();
            ret.add(elem);
        }
        return ret;
    }

    public ItemsPage getItems(WebElement category) {
        category.click();
        return new ItemsPage(driver);
    }

    public PendingItemsPage getPendingItems() {
        pendingItemsBtn.click();
        return new PendingItemsPage(driver);
    }

    public CustomerZonePage openCustomerZone() {
        customerZone.findElement(customerZoneLink).click();
        return new CustomerZonePage(driver);
    }
}

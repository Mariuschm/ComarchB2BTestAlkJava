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

import static java.lang.Thread.sleep;

public class MainPage extends BasePage {
    @FindBy(xpath = "//i[@class='ti-briefcase']/following-sibling::*")
    private WebElement customerName;
    @FindBy(xpath = "//i[@class='ti-lock']/following-sibling::*")
    private WebElement employeeName;
    @FindBy(xpath = "//i[@class='ti-power-off']")
    private WebElement logoutButton;
    private final By categories = By.xpath("//li[@class='category-item inner-clear']");
    private final String categoryName = "(//li[@class='category-item inner-clear'])[{0}]//a[@class='group-label outline button f-left']";

    public MainPage(WebDriver driver) {
        super(driver);
        //Set implicitly wait for 1 s.
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
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
        logoutButton.click();
    }

    /**
     * Returns list o categories on page
     *
     * @return list o Webelements
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
        var index = 1;
        for (WebElement category : getCategories()
        ) {
            var elem = new CategoryWithName();
            elem.Category = category;
            elem.Name = String.valueOf(category.findElement(By.xpath(String.format(categoryName, index))));
            ret.add(elem);
            index++;
        }
        return ret;
    }

    public ItemsPage getItems(WebElement category) {
        category.click();
        return new ItemsPage(driver);
    }

}

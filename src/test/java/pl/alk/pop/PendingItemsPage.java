package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PendingItemsPage extends BasePage {


    private final By itemsList = By.cssSelector("div[class^='grid-table']");
    private final By totalCount = By.cssSelector("span[class*='grid-cell-full-row']");
    private final By items = By.cssSelector("div[class='grid-row body-row']");

    public PendingItemsPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Returns number of pending items
     * @return size of items array
     */
    public int getPendingItemsCount() {
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait)).until(ExpectedConditions.visibilityOfElementLocated(itemsList));
        var list = this.driver.findElement(itemsList);
        return list.findElements(items).size();
    }

    /***
     * Returns total count of items from label
     * @return value from label
     */
    public int getPendingItemsTotal() {
        var searchString = ":";
        new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait)).until(ExpectedConditions.visibilityOfElementLocated(totalCount));
        var total = this.driver.findElement(totalCount);
        var labelText = total.getText();
        var index = labelText.indexOf(searchString) + searchString.length() + 1;
        var ret = labelText.substring(index);
        try {
            return Integer.parseInt(ret);
        } catch (NumberFormatException e) {
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

}

package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PendingItemsPage extends BasePage {
    @FindBy(css = "div[class^='grid-table']")
    private WebElement itemsList;

    @FindBy(css = "input[id='name-field']")
    private WebElement searchByName;

    @FindBy(css = "span[class*='grid-cell-full-row']")
    private WebElement totalCount;
    private final By items = By.cssSelector("div[class='grid-row body-row']");
    public PendingItemsPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Returns number of pending items
     * @return size of items array
     */
    public int getPendingItemsCount(){
        return itemsList.findElements(items).size();
    }

    /***
     * Returns total count of items from label
     * @return value from label
     */
    public int getPendingItemsTotal(){
        var searchString =":";
        var labelText = totalCount.getText();
        var index = labelText.indexOf(searchString)+searchString.length();
        var ret = labelText.substring(index);
        try{
            return Integer.parseInt(ret);
        }
        catch (NumberFormatException e){
            return 0;
        }
        catch (Exception e){
            return -1;
        }
    }

}

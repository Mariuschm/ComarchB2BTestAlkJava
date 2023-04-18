package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemsPage  extends BasePage{
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
    protected By getProducts=(By.cssSelector("div[class=\"item-info name\"]"));
    public ItemsPage(WebDriver driver) {
        super(driver);
    }
    public void setThumbnailView(){
        setThumbnailViewBtn.click();
    }

    public void setListView(){
        setListViewBtn.click();
    }

    public void setDetailsVie(){
        setDetailsViewBtn.click();
    }

    /***
     * Returns display-type of current view
     * @return 0 - details view, 1- list view, 2-thumbnail view
     */
    public int getStyleId(){
        var divClass = productsContainer.getAttribute("class");
        var searchString = "display-type-";
        var position = divClass.indexOf(searchString)+searchString.length();
        var ret =divClass.substring(position, position +1);
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

    public int getItemsCount(){
        var items = productsList.findElements(getProducts);
        return items.size();
    }
}

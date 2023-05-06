package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.model.DownloadItem;
import pl.alk.utils.Config;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class CustomerZonePage extends BasePage {
    protected By downloads = By.cssSelector("a[class*='files']");
    protected By fileList = By.cssSelector("div[class='grid-table clear']");
    protected By file = By.cssSelector("div[class='grid-row body-row']");
    protected By link = By.cssSelector("a[class='covering-link']");
    protected By fileInfo = By.cssSelector("div[class='body-cell fileName fullName grid-cell']");
    @FindBy(css = "app-profile-menu[class*='sticky']")
    private WebElement menu;

    public CustomerZonePage(WebDriver driver) {
        super(driver);
    }

    /***
     * Returns list of Webelements with description of files to download
     * @return Array List of DownloadItems
     */
    public ArrayList<DownloadItem> getFiles() {
        //Go to download section
        menu.findElement(downloads).click();
        //Get list of files
        var list = new ArrayList<DownloadItem>();
        for (WebElement file :
                driver.findElement(fileList).findElements(file)) {
            var item = new DownloadItem();
            item.link = file.findElement(link);
            item.fileName = file.findElement(fileInfo).getText();
            list.add(item);
        }
        return list;
    }

    /***
     * Downloads selected file. After assertion file is deleted
     * @param item file info
     * @return true if file was downloaded , false if file was not downloaded in specific time
     */
    public boolean downloadFile(DownloadItem item) {
        //Download file
        File file = new File(Config.DOWNLOAD_PATH + item.fileName);
        if (file.exists())
            file.delete();
        item.link.click();
        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait))
                    .until((ExpectedCondition<Boolean>) webDriver -> file.exists());
            //Delete the file
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

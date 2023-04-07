package pl.alk.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class MainPage extends  BasePage {
    @FindBy(xpath = "//i[@class='ti-briefcase']/following-sibling::*")
    private WebElement customerName;
    @FindBy(xpath = "//i[@class='ti-lock']/following-sibling::*")
    private WebElement employeeName;
    @FindBy(xpath = "//i[@class='ti-power-off']")
    private WebElement logoutButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedUser() {
        return customerName.getText();
    }

}

package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.model.LoginResult;
import pl.alk.utils.Core;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By termsNotAcceptedError = By.cssSelector("small[class$='validation-error danger']");
    private final By loginErrorMessage = By.cssSelector("div[class='danger text-center']");
    private final By termsAndConditionsBy = By.xpath("//span[@class='vmiddle']");

    @FindBy(id = "customerName-field")
    private WebElement customerNameInput;
    @FindBy(id = "userName-field")
    private WebElement userNameInput;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    @FindBy(css = ".primary-action")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        //Wait for all items to be visible
        wait.until(ExpectedConditions.visibilityOf(customerNameInput));
        wait.until(ExpectedConditions.visibilityOf(userNameInput));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    /**
     * Login to Comarch B2B. When credentials are valid MainPage is returned;
     * If any error appears method returns null.
     *
     * @param companyName Company name
     * @param userName    User name
     * @param password    Password
     * @param acceptTerms Do you accept terms
     * @return returns LoginResult class, MainPage gets null on error, possible result values
     * 1 - ok
     * -1 - invalid login data
     * -2 - terms and conditions not accepted
     * - 3 - could not locate terms and conditions check
     */
    public LoginResult login(String companyName, String userName, String password, boolean acceptTerms) {
        var res = new LoginResult();
        Core.isLogged = false;
        customerNameInput.sendKeys(companyName);
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        if (acceptTerms) {
            try {
                new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait)).until(ExpectedConditions.visibilityOfElementLocated(termsAndConditionsBy));
                var termsAndConditions = driver.findElement(termsAndConditionsBy);
                termsAndConditions.click();
            } catch (TimeoutException err) {
                res.setPage(null);
                res.setResult(-3);
                return res;
            }
        }

        loginButton.click();
        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            var loginError = driver.findElement(loginErrorMessage);
            if (loginError.isDisplayed()) {
                res.setPage(null);
                res.setResult(-1);
            }
            return res;
        } catch (TimeoutException ignored) {
        }
        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(termsNotAcceptedError));
            var termsNotAccepted = driver.findElement(termsNotAcceptedError);
            if (termsNotAccepted.isDisplayed()) {
                res.setPage(null);
                res.setResult(-2);
                return res;
            }
        } catch (TimeoutException ignored) {
        }

        res.setPage(new MainPage(driver));
        res.setResult(1);
        Core.isLogged = true;
        return res;
    }

}

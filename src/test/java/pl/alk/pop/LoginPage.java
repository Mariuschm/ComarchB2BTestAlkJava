package pl.alk.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.model.LoginResult;

import java.time.Duration;

public class LoginPage extends BasePage {
    @FindBy(id = "customerName-field")
    private WebElement customerNameInput;
    @FindBy(id = "userName-field")
    private WebElement userNameInput;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//span[@class='vmiddle']")
    private WebElement termsAndConditions;
    @FindBy(css = ".primary-action")
    private WebElement loginButton;
    private final By loginErrorMessage = By.cssSelector("small[class$='danger']");
    private final By termsNotAcceptedError = By.cssSelector("div[class='danger text-center']");


    public LoginPage(WebDriver driver) {
        super(driver);
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
     * -1 - invalid login daa
     * -2 - terms and conditions not accepted
     */
    public LoginResult login(String companyName, String userName, String password, boolean acceptTerms) {
        var res = new LoginResult();
        customerNameInput.sendKeys(companyName);
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        if (acceptTerms)
            termsAndConditions.click();
        loginButton.click();
        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            var loginError = driver.findElement(loginErrorMessage);
            if (loginError.isDisplayed()) {
                res.setPage(null);
                res.setResult(-1);
            }
            return res;
        } catch (TimeoutException e) {
        }
        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(termsNotAcceptedError));
            var termsNotAccepted = driver.findElement(termsNotAcceptedError);
            if (termsNotAccepted.isDisplayed()) {
                res.setPage(null);
                res.setResult(-2);
                return res;
            }
        } catch (TimeoutException e) {
        }

        res.setPage(new MainPage(driver));
        res.setResult(1);
        return res;
    }

}

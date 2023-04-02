package pl.alk.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id ="customerName-field")
    private WebElement customerNameInput;
    @FindBy(id ="userName-field")
    private WebElement userNameInput;
    @FindBy(xpath ="//input[@type='password']")
    private WebElement passwordInput;
    @FindBy(xpath ="//span[@class='vmiddle']")
    private WebElement termsAndConditions;
    @FindBy(xpath = "/button[@class='action primary-action'")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class='danger text-center']")
    private WebElement loginErrorMessage;
    @FindBy(xpath = "/small[@class='validation-error danger']")
    private WebElement termsNotAcceptedError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login to Comarch B2B. When credentials are valid MainPage is returned;
     * If any error appears method returns null.
     * @param companyName Company name
     * @param userName User name
     * @param password Password
     * @param acceptTerms Do you accept terms
     * @return Main page if ok, null if error
     */
    public MainPage login(String companyName, String userName, String password, boolean acceptTerms){
        customerNameInput.sendKeys(companyName);
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        if (acceptTerms)
            termsAndConditions.click();
        loginButton.click();
        return new MainPage();
    }

}

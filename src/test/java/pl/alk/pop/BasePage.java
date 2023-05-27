package pl.alk.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.alk.utils.Core;

import java.time.Duration;

public class BasePage extends Core {
    protected final byte defaultWait = 30; //default defaultWait for selectors, this page loads slow
    protected WebDriver driver;
    protected WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait));
        PageFactory.initElements(this.driver, this);
    }
}


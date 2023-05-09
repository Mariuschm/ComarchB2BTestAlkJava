package pl.alk.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pl.alk.utils.Core;

public class BasePage extends Core {
    protected final byte defaultWait = 30; //default defaultWait for selectors, this page loads slow
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}


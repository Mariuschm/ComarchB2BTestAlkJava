package pl.alk.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pl.alk.utils.Core;

public class BasePage extends Core {
    protected WebDriver driver;
    protected final byte defaultWait =20; //default defaultWait for selectors, this page loads slow
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
}


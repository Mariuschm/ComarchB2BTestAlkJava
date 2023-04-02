package pl.alk.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Core {
    protected static WebDriver driver;

    /**
     * Create and retrun browser driver
     * @param browser firefox, chrome, chromium, edge
     * @return
     */
    public static WebDriver setDriver(String browser)
    {
        switch (browser.toLowerCase())
        {
            case  "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case  "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "chromium":
                driver =  WebDriverManager.chromiumdriver().create();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                driver = WebDriverManager.chromedriver().create();
                break;
        }
        return driver;
    }

    /**
     * Creates a screenshot of current view
     * @param filename destination filename
     */
    public void getScreenshot(String filename){
        File dest = new File(Config.IMAGE_PATH + filename);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "JPG", dest);
        }catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }

    /**
     * Creates a screenshot of error
     * @param filename destination name
     */
    public void getScreenshotError(String filename){
        File dest = new File(Config.IMAGE_PATH_ERROR + filename);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "JPG", dest);
        }catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }
}

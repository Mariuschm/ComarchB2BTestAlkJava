package pl.alk.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;
import org.testng.Reporter;
import pl.alk.model.ItemModel;
import pl.alk.model.LoginDataModel;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Core {
    protected static WebDriver driver;

    /**
     * Create and return browser driver
     *
     * @param browser firefox, chrome, chromium, edge
     * @return driver in correct type
     */
    public static WebDriver setDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chromium":
                driver = WebDriverManager.chromiumdriver().create();
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
     *
     * @param filename destination filename
     */
    public void getScreenshot(String filename) {
        File dest = new File(Config.IMAGE_PATH + filename);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "JPG", dest);
        } catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }

    /**
     * Creates a screenshot of error
     *
     * @param filename destination name
     */
    public void getScreenshotError(String filename) {
        File dest = new File(Config.IMAGE_PATH_ERROR + filename);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "JPG", dest);
        } catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }

    /**
     * Returns login data from CSV file
     *
     * @param filename CSV filename
     * @return LoginDataModel object
     */
    public LoginDataModel getLoginData(String filename) {
        LoginDataModel ret = new LoginDataModel();
        try {
            var sep = ";";
            var filereader = new FileReader(filename);
            CsvToBean<LoginDataModel> csvReader = new CsvToBeanBuilder(filereader)
                    .withType(LoginDataModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(sep.charAt(0))
                    .build();
            var csvIterator = csvReader.iterator();
            while (csvIterator.hasNext()) {
                ret = csvIterator.next();
            }
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns a list of items to be added to cart
     *
     * @param filename filename
     * @return List of items
     */
    public List<ItemModel> getItemsList(String filename) {
        List<ItemModel> ret = null;
        try {
            var sep = ";";
            var filereader = new FileReader(filename);
            CsvToBean<ItemModel> csvReader = new CsvToBeanBuilder(filereader)
                    .withType(ItemModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(sep.charAt(0))
                    .build();
            var csvIterator = csvReader.iterator();
            while (csvIterator.hasNext()) {
                ret.add(csvIterator.next());
            }
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns iterator with data for test
     *
     * @param filename source file name
     * @return data for test
     */
    public Iterator<Object[]> getItemsIterator(String filename) {
        String[] data = null;
        String line = null;
        List<Object[]> lines = new ArrayList<>();
        Iterator<Object[]> res = null;
        try {
            var sep = ";";
            var filereader = new FileReader(filename);
            var csvReader = new BufferedReader(filereader);
            while ((line = csvReader.readLine()) != null) {
                lines.add(line.split(sep));
            }
            res = lines.iterator();
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

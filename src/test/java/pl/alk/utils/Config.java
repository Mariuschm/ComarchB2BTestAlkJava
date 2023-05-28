package pl.alk.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    public static final String IMAGE_PATH =
            new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots/";
    public static final String IMAGE_PATH_ERROR =
            new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots/errors/";
    public static final String BROWSER = "chrome";
    public static final URL REMOTE_DRIVER;

    static {
        try {
            REMOTE_DRIVER = new URL("http://localhost:4444/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final char CSV_SEPARATOR = ';';
    public static final String DOWNLOAD_PATH = new File(System.getProperty("user.home")).getAbsolutePath() + "/downloads/";

}

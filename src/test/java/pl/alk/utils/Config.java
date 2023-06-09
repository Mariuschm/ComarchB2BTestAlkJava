package pl.alk.utils;

import java.io.File;

public class Config {
    public static final String IMAGE_PATH =
            new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots/";
    public static final String IMAGE_PATH_ERROR =
            new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots/errors/";
    public static final String BROWSER = "edge";
    public static final char CSV_SEPARATOR = ';';
    public static final String DOWNLOAD_PATH = new File(System.getProperty("user.home")).getAbsolutePath() + "/downloads/";

}

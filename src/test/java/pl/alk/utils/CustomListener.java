package pl.alk.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListener extends Core implements ITestListener {
    @Override
    /**
     * Saves screen of error
     */
    public void onTestFailure(ITestResult result) {
        var file   = Config.IMAGE_PATH_ERROR+result.getMethod().getMethodName()+".JPG";
        getScreenshot(file);
        Reporter.log("<a href='"+file+"'target ='_blank'><img src='"+file+"' width=100 height=100/></a>");

    }
}

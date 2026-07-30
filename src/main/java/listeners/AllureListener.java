package listeners;

import com.microsoft.playwright.Page;
import core.PlaywrightFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        Page page = PlaywrightFactory.getPage();

        ScreenshotUtil.capture(page, result.getMethod().getMethodName());
    }
}

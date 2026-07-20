package core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.nio.file.Paths;

public class BaseTest {
    protected Page page;
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chromium") String browser){
        page = PlaywrightFactory.initBrowser(browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            String tracefile = "traces/" + result.getMethod().getMethodName() + "_"
                    + System.currentTimeMillis() + ".zip";

            PlaywrightFactory.saveTraces(tracefile);
            System.out.println("Test Failed - Trace saved to " + Paths.get(tracefile).toAbsolutePath());
        }
        PlaywrightFactory.tearDown();
    }
}

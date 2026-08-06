package retry;

import core.ConfigReader;
import io.qameta.allure.Allure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int retryCount =  ConfigReader.retryCount();
    private static final int MAX_RETRY_COUNT = 0;

    @Override
    public boolean retry(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if(throwable instanceof AssertionError){
            return false;
        }
        if(retryCount < MAX_RETRY_COUNT){
            retryCount++;
            System.out.println("Retrying " + result.getMethod().getMethodName()
            + "Attempt number: " + (retryCount) + "...");

            Allure.step("Retrying " + result.getMethod().getMethodName()
                    + "Attempt number: " + (retryCount) + "...");
            return true;
        }

        return false;
    }
}

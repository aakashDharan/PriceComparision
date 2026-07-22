package TestTestCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class test {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeMethod
    public void setup(){
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        page = browser.newPage();
    }

    @AfterMethod
    public void tearDown(){
        browser.close();
        playwright.close();
    }

    @Test
    public void checkLocators() throws InterruptedException {
        page.navigate("https://www.airbnb.co.in/");
        System.out.println("Title: " + page.title());

        page.getByRole(
          AriaRole.BUTTON,
          new Page.GetByRoleOptions().setName("Close")
        ).click();

        Thread.sleep(5000);
    }

}

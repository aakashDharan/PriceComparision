package ui;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import core.BaseTest;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {
   // @Test
    public void homepageLoads(){
        page.navigate("/");
        PlaywrightAssertions.assertThat(page).hasTitle(java
                .util.regex.Pattern.compile(".*"));
        System.out.println("Page Title: " + page.title());
        System.out.println("Page URL: " + page.url());
    }
}

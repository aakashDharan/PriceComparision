package ui;

import core.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;

public class SearchFunctionalityTest extends BaseTest {


    @Test
    public void getOntoHomePage() {
        HomePage homepage = new HomePage(page);
        boolean isPopupPresent;

        step("Navigate to the web page.");
        page.navigate("/");

        isPopupPresent =  step("Check for 'Welcome' popup",
               homepage::isPopupPresent);

        if(isPopupPresent){
            step("Closed the 'Welcome' pop up.");
        }else {
            step("No 'Welcome' is present.");
        }

        step("Verifying if the logo is present.", () -> {
            assertThat(homepage.logo()).isVisible();
        });


    }


}

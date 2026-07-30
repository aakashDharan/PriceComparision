package ui;

import components.PropertyCard;
import core.BaseTest;
import io.qameta.allure.Allure;
import models.SearchCriteria;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.*;

//@Listeners(TestListener.class)
public class SearchFunctionalityTest extends BaseTest {


    @Test
    public void getOntoHomePage() {
        String destination = "Canacona";
        String searchText = "can";
        int guestNum = 2;
        String searchResult;

        SearchCriteria searchParameters = new SearchCriteria(searchText, destination, guestNum);
        HomePage homepage = new HomePage(page);
        //SearchResultPage result = new SearchResultPage(page);

        boolean isPopupPresent;

        step("Navigate to the web page.", () ->{
            page.navigate("/");
        });


        isPopupPresent =  step("Check for 'Welcome' popup",
               homepage::isPopupPresent);

        if(isPopupPresent){
            step("Closed the 'Welcome' pop up.");
            homepage.closePopup();
        }else {
            step("No 'Welcome' is present.");
        }

        step("Verifying if the logo is present.", () -> {
            assertThat(homepage.logo()).isVisible();
        });

        step("Searching for " + guestNum + " guests in " + destination);
        SearchResultPage result = homepage.search(searchParameters);
        searchResult = result.getTitle();
        result.acceptDialog();

        step("Search Successful!!", () ->{
             assertTrue(containsText(searchResult,destination));
        });

        List<PropertyCard> cards = result.getPropertyCards();
        //System.out.println(cards.get(0).getName());

        step("Properties are displayed.");
        for (PropertyCard card : cards) {
            assertNotNull(card.getName());
            //System.out.println(card.getName());
            assertFalse(card.getName().trim().isEmpty());
        }
    }
}

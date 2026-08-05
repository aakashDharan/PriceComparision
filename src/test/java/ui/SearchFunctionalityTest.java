package ui;

import components.PropertyCard;
import core.BaseTest;
import models.SearchCriteria;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PropertyPage;
import pages.SearchResultPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.*;

//@Listeners(TestListener.class)
public class SearchFunctionalityTest extends BaseTest {

    String destination = "Canacona";
    String searchText = "can";
    int guestNum = 2;

    PropertyPage property;
    List<PropertyCard> cards;

    @Test
    public void getOntoHomePage() {

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
        System.out.println("Search complete");
        searchResult = result.getTitle();
        System.out.println("Searched: " + searchResult);
        if(result.isPopupPresent()){
            result.acceptDialog();
        }

        step("Search Successful!!", () ->{
             assertTrue(containsText(searchResult,destination));
        });

        cards = result.getPropertyCards();
        //System.out.println(cards.get(0).getName());

        step("Properties are displayed.");
        for (PropertyCard card : cards) {
            assertNotNull(card.getName());
            //System.out.println(card.getName());
            assertFalse(card.getName().trim().isEmpty());
        }

        for(PropertyCard card : cards){
            property = card.open();
            System.out.println(property.getPropertyName());
            property.close();
        }
    }


    @Test
    public void checkingPropertyData(){
        page.navigate("/rooms/1708422716504775101?adults=2&search_mode=regular_search&source_impression_id=p3_1785910144_P3ujmhGcs0KbX7mb&previous_page_section_name=1000&federated_search_id=b4b081ea-6bf8-463b-abb2-dd7f63ec01c1&guests=2&check_in=2026-08-05&check_out=2026-08-06");
       // page.pause();
        property = new PropertyPage(page);
        property.waitForPage();
        System.out.println(property.getPropertyName());
    }

}

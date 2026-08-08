package ui;


import components.PropertyCard;
import core.BaseTest;
import models.PropertyData;
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

    SearchCriteria searchParameters = new SearchCriteria(searchText, destination, guestNum);

    PropertyPage property;
    List<PropertyCard> cards;

    @Test
    public void getOntoHomePage() {

        String searchResult;

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
        System.out.println(cards.size());
        //System.out.println(cards.get(0).getName());

        step("Properties are displayed.");
        for (PropertyCard card : cards) {
            assertNotNull(card.getName());
            //System.out.println(card.getName());
            assertFalse(card.getName().trim().isEmpty());
        }

        for(PropertyCard card : cards){
            property = card.open();
            PropertyData details = property.getDetails();
            System.out.println(details);
            property.close();
        }
    }


    @Test
    public void checkingPropertyData() {
        page.navigate("rooms/1570019304345419706?adults=2&check_in=2026-08-09&check_out=2026-08-10&search_mode=regular_search&category_tag=Tag%3A8678&photo_id=2447276647&source_impression_id=p3_1786212646_P3o5SxxwYdsMKXAb&previous_page_section_name=1000&federated_search_id=8fcba9b4-f448-474b-82e8-1b549e33f2d0");
        Integer i = 3;
        PropertyPage property = new PropertyPage(page);

        System.out.println(property.getPropertyName());
        PropertyData page = property.getDetails();
        System.out.println(page);

    }


}

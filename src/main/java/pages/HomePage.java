package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import models.SearchCriteria;

import java.time.LocalDate;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage extends BasePage{

    private final Locator popup;
    private final Locator popupCross;
    private final Locator homePageLogo;
    private final Locator destination;
    private final Locator desSuggestions;
   // private final Locator checkInDate;
   // private final Locator checkOutDate;
    private final Locator searchBtn;
    private final Locator guest;
    private final Locator guestIncrease;

   // private String searchText;
    private String actualDes;

    public HomePage(Page page) {
        super(page);

        popup = page.getByRole(AriaRole.DIALOG);
        popupCross = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Close")
        );
        homePageLogo = page.getByLabel("Airbnb homepage");

        destination = page.getByPlaceholder("Search destinations");
        desSuggestions = page.getByRole(AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(actualDes));
        //checkInDate = dateLocator(LocalDate.now());
        //checkOutDate = dateLocator(LocalDate.now().plusDays(2));
        searchBtn = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Search")
        );

        guest = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Who Add guests")
        );

        guestIncrease = page.getByTestId("stepper-adults-increase-button");
    }

    public HomePage waitForPage(){
        homePageLogo.waitFor();
        //homePageLogo.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return this;
    };




    //Checks if any popup is present for further tests
    public boolean isPopupPresent() {
        waitForPage();
      if(popup.isVisible()){
          return true;
      }else{
          try{
              popup.waitFor(new Locator.WaitForOptions()
                      .setState(WaitForSelectorState.VISIBLE)
                      .setTimeout(2000));

              return true;
          } catch(PlaywrightException e){
              return false;
          }
      }
    }

    //Close the popup
    public void closePopup(){
        popupCross.click();
    }

    //Checks the company logo is present
    public Locator logo(){
       return homePageLogo;
    }

    private void enterDestination(String searchText, String actualDes){
        destination.click();
        destination.pressSequentially(searchText);

        //assertThat(desSuggestions.first()).isVisible();

        desSuggestions.filter(new Locator.FilterOptions().setHasText(actualDes))
                .first()
                .click();
    }



    public SearchResultPage search(SearchCriteria values) {
        enterDestination(values.getSearchTxt(), values.getDestination());
        selectDates(LocalDate.now(), LocalDate.now().plusDays(1));
        guest.click();
        guestNum(values.getGuestNum());
        searchBtn.click();
        SearchResultPage resultPage = new SearchResultPage(page);
        resultPage.waitForpage();
        return resultPage;
    }

    private void selectDates(LocalDate checkIn, LocalDate checkOut) {
        dateLocator(checkIn).click();
        dateLocator(checkOut).click();
    }

   private void guestNum(int num){
        for(int i = 0; i< num; i++){
            guestIncrease.click();
        }
    }

//    public boolean ispageLoaded(Locator newPage){
//        newPage.
//    }
}

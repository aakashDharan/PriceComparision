package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import components.PropertyCard;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    //private final Locator property;
    private final Locator popup;
    private final Locator popupCross;
    private final Locator dialogAccept;
    private final Locator heading;
    private final Locator propertyCards;

    public SearchResultPage(Page page) {
        super(page);
        //property =

        popupCross= page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Close"));

        popup = page.getByRole(AriaRole.DIALOG,
                new Page.GetByRoleOptions()
                        .setName("Now you’ll see one price for your trip, all fees included."));
        //popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        dialogAccept = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Got it"));

       heading = page.getByTestId("stays-page-heading");


        propertyCards = page.getByTestId("card-container");
    }


    public SearchResultPage waitForpage(){
        page.waitForURL("**/s/**");

        heading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        return this;
    }

    public boolean isPopupPresent(){
        try {
            popup.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(2000));
            return true;
        } catch (TimeoutError e) {
            return false;
        }
    }
    public void acceptDialog(){
        dialogAccept.click();
    }
    public String getTitle(){
        //Locator heading = getHeading();
        heading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return heading.textContent();
    }

    public List<PropertyCard> getPropertyCards(){
        List<PropertyCard> cards = new ArrayList<>();
        for(Locator card : propertyCards.all()){
            cards.add(new PropertyCard(card, page));
        }
        return cards;
    }
}

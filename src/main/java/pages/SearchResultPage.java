package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class SearchResultPage extends BasePage {

    //private final Locator property;
    private final Locator popup;
    private final Locator popupCross;
    private final Locator dialogAccept;
    private final Locator heading;

    public SearchResultPage(Page page) {
        super(page);
        //property =

        popupCross= page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Close"));

        popup = page.getByRole(AriaRole.DIALOG,
                new Page.GetByRoleOptions()
                        .setName("Now you’ll see one price for your trip, all fees included."));
        popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        dialogAccept = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Got it"));

        heading = page.getByTestId("stays-page-heading");
    }


    public boolean isPopupPresent(){
        try {
            popup.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(3000));
            return true;
        } catch (TimeoutError e) {
            return false;
        }
    }
    public void acceptDialog(){
        dialogAccept.click();
    }
    public String getTitle(){
        heading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return heading.textContent();
    }
}

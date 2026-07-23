package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage extends BasePage{

    private final Locator popup;
    private final Locator popupCross;
    private final Locator homePageLogo;

    public HomePage(Page page) {
        super(page);

        popup = page.getByRole(AriaRole.DIALOG);
        popupCross = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Close")
        );
        homePageLogo = page.getByLabel("Airbnb homepage");
    }

    public HomePage waitForPage(){
        homePageLogo.waitFor();
        return this;
    };

    //Checks if any popup is present for further tests
    public boolean isPopupPresent() {
      if(popup.isVisible()){
          return true;
      }else{
          try{
              popup.waitFor(new Locator.WaitForOptions()
                      .setState(WaitForSelectorState.VISIBLE)
                      .setTimeout(3000));

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

}

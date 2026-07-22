package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BaseClass{

    private final Locator popup;
    private final Locator closePopup;

    public HomePage(Page page) {
        super(page);

        popup = page.getByRole(AriaRole.DIALOG);
        closePopup = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Close")
        );
    }

    public boolean isPopupPresent(){
        if(popup.isVisible()){
            System.out.println("Popup is Present.");
            return true;
        }else{
            return false;
        }

    }



}

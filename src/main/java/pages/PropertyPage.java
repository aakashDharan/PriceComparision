package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PropertyPage extends BasePage {

    public PropertyPage(Page page) {
        super(page);
    }


    public String getPropertyName() {
        return page.locator("//div[@data-section-id='TITLE_DEFAULT']").textContent();
    }

    public PropertyPage waitForPage() {
        page.waitForLoadState();
        //page.waitForURL("**search_id**");
       //page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        Locator title = page.locator("[data-section-id='TITLE_DEFAULT']");
        title.waitFor();
        assertThat(title).not().containsText("");
        return this;
    }

    public void close(){
        page.close();
    }
}

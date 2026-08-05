package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import pages.PropertyPage;

public class PropertyCard {
    private final Locator root;
    private final Page page;

    public PropertyCard(Locator root, Page page) {
        this.root = root;
        this.page = page;

    }

    public String getName(){
        return root.getByTestId("listing-card-name").textContent();
    }

    public PropertyPage open(){
        Page propertytab = page.context().waitForPage(() ->{
            root.click();
        });

        propertytab.waitForLoadState();
        return new PropertyPage(propertytab).waitForPage();

    }
}

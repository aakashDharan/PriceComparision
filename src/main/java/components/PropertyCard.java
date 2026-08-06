package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import org.json.JSONObject;
import pages.PropertyPage;

import java.util.concurrent.atomic.AtomicReference;

public class PropertyCard {
    private final Locator root;
    private final Page page;
    AtomicReference<JSONObject> graphQl = new AtomicReference<>();

    public PropertyCard(Locator root, Page page) {
        this.root = root;
        this.page = page;

    }

    public String getName(){
        return root.getByTestId("listing-card-name").textContent();
    }

    public PropertyPage open(){
        Page propertyTab = page.context().waitForPage(root::click);


        propertyTab.waitForLoadState();
        return new PropertyPage(propertyTab).waitForPage();

    }
}

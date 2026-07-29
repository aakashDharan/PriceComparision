package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
}

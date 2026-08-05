package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import core.PlaywrightFactory;
import models.PropertyData;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PropertyPage extends BasePage {



    public PropertyPage(Page page) {
        super(page);
    }


    public String getPropertyName() {
        return page.locator("[data-section-id='TITLE_DEFAULT'] h1").textContent();
    }

    public PropertyPage waitForPage() {
       // Locator img = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Wake up to natural light,"));
        Locator img = page.locator("[id='FMP-target']");
        img.waitFor();
        return this;
    }


     public PropertyData getDetails(){

         JSONObject response = PlaywrightFactory.getNetworkMonitor().getPropertyResponse();



     }


    public void close(){
        page.close();
    }
}

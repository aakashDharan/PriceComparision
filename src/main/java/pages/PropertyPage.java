package pages;



import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.AriaRole;
import core.ConfigReader;

import org.json.JSONObject;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PropertyPage extends BasePage {
    private JSONObject propertyResponse;


    public PropertyPage(Page page) {
        super(page);
    }



    public PropertyPage waitForPage() {
       // Locator img = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Wake up to natural light,"));
        Locator img = page.locator("[id='FMP-target']");
        img.waitFor();
        return this;
    }

    public String getPropertyName() {
        return page.locator("[data-section-id='TITLE_DEFAULT'] h1").textContent();
    }

    public String getHostName(){
        Locator hName = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setLevel(2));
        return hName.textContent();
    }


//     public PropertyData getDetails(){
//         ApiRequest request =
//                 createApiRequest();
//
//
//     }



    public void close(){
        page.close();
    }
}

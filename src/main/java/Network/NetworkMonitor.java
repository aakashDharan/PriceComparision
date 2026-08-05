package Network;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Response;
import org.json.JSONObject;

public class NetworkMonitor {
    private JSONObject propertyResponse;

    public void attach(BrowserContext context){
        context.onResponse(this::capture);
    }

    private void capture(Response response) {
        if(response.url().contains("StaysPdpSections")){
            propertyResponse = new JSONObject(response.text());
        }
    }
    public JSONObject getPropertyResponse(){
        return propertyResponse;
    }
}

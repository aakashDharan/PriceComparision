package pages;



import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.TimeoutError;
import models.PropertyData;
import org.json.JSONObject;


public class PropertyPage extends BasePage {
    private JSONObject propertyResponse;


    public PropertyPage(Page page) {
        super(page);
        System.out.println("constructor called");
    }



    public PropertyPage waitForPage() {
        System.out.println("wait for Pae called");
       // Locator img = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Wake up to natural light,"));
        Locator img = page.locator("[id='FMP-target']");
        System.out.println("Image located");
        img.waitFor();
        System.out.println("Image waited for");
        return this;
    }

    public String getPropertyName() {
        return page.locator("[data-section-id='TITLE_DEFAULT'] h1").textContent();
    }

    public String getHostName(){
        Locator hName = page.locator("[data-section-id='MEET_YOUR_HOST'] h2 span ");
        System.out.println(1);
        return hName.textContent();
    }

    public int getReviewCount(){
        Locator reviewCountLoc = page.getByTestId("Reviews-stat-heading");
        String reviewCount = reviewCountLoc.textContent();
        if(reviewCount == null || reviewCount.isEmpty()){
            return 0;
        }
        System.out.println(2);
        return Integer.parseInt(reviewCount);
    }

    public double getRating(){
        Locator ratingLoc = page.getByTestId("Rating-stat-heading");
        String rating = ratingLoc.textContent();
        if (rating == null || rating.isBlank()) {
            return 0.0;
        }
        System.out.println(3);
        return Double.parseDouble(rating);
    }

    public int getPrice(){
        Locator priceLoc = page.locator("[data-testid='book-it-hover-target'] [aria-haspopup='dialog'] span").first();
        String price = priceLoc.textContent()
                .replace("₹", "")
                .replace(",", "")
                .trim();

        System.out.println(4);
        return Integer.parseInt(price);
    }

    public Integer getMaxGuest(){
        Locator guests = page.locator("li")
                .filter(new Locator.FilterOptions().setHasText("guest"));

        if (guests.count() == 0) {
            return -1;    // or -1 if you prefer
        }

        int guestCount = Integer.parseInt(
                guests.textContent()
                        .replaceAll("\\D+", "")
        );
        System.out.println(5);
        return guestCount;
    }

    public String getLocation(){
//        Locator locationLoc = page.locator("[data-section-id=LOCATION_DEFAULT] div")
//                .filter(new Locator.FilterOptions().setHasText("India"));
        Locator locationLoc = page.locator("[data-section-id='LOCATION_DEFAULT'] div:text-matches('.*,.*,.*')");

        String fullLoc =  locationLoc.textContent();

        String[] loc = fullLoc.split(",");
        System.out.println(6);
        return loc[0];
    }


     public PropertyData getDetails(){
         PropertyData data = new PropertyData();
        System.out.println("GET PROPERTY CALLED");

        Integer maxGuest = getMaxGuest();
        data.setHost(getHostName());
        data.setLocation(getLocation());
        data.setMaxGuest(maxGuest);
        data.setName(getPropertyName());
        data.setPrice(getPrice());
        data.setRating(getRating());
        data.setReviewCount(getReviewCount());

        if(maxGuest>2){
            for(int i = 2; i<4;i++){
                data.setGuestPrices(i,getPriceForGuest(i));
            }
        }else{
            data.setGuestPrices(2,getPriceForGuest(2));
        }


        return data;
     }
    public Integer getPriceForGuest(Integer guestNum){
        Locator selectedGuestLoc = page.locator("#GuestPicker-book_it-trigger");
        int selectedGuest = Integer.parseInt(selectedGuestLoc
                .textContent().replaceAll("\\D+", ""));

        if (selectedGuest == guestNum) {
            return getPrice();
        }

        Locator priceLoc = page.locator("[data-testid='book-it-hover-target'] [aria-haspopup='dialog'] span").first();
        String oldPriceText = priceLoc.textContent();

        selectedGuestLoc.click();
        Locator plusBtnLoc = page.getByTestId("GuestPicker-book_it-form-adults-stepper-increase-button");
        while (selectedGuest < guestNum) {
            plusBtnLoc.click();
            selectedGuest++;
        }

        try {
            page.waitForCondition(() -> !priceLoc.textContent().equals(oldPriceText),
                    new Page.WaitForConditionOptions().setTimeout(1000));
        } catch (TimeoutError e) {
            return getPrice();
        }

        return getPrice();
    }

    public void close(){
        page.close();
    }
}

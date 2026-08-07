package models;

import java.util.HashMap;
import java.util.Map;

public class PropertyData {
    private String name;
    private String host;
    private double rating;
    private Integer  maxGuest;
    private Map<Integer, Integer> guestPrices = new HashMap<>();
    private int reviewCount;
    private int price;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString(){
        return "PropertyData {" +
                "Property Name = '" + name + "', " +
                "Host Name = '"+ host + ", " +
                "Rating = " + rating + ", " +
                "Prices for guest: " + guestPrices +", " +
                "Review Number = " + reviewCount + ", " +
                "Location = " + location +", "+
                "Price = " + price + ", " +
                "Max Guest = " + maxGuest +" }";
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public Map<Integer, Integer> getGuestPrices() {
        return guestPrices;
    }

    public void setGuestPrices(Integer key, Integer value) {
        guestPrices.put(key,value);
    }
}

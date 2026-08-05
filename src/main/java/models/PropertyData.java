package models;

public class PropertyData {
    private String name;
    private String host;
    private double rating;
    private int reviewCount;
    private String price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
        return "PropertyData{" +
                "Property Name = '" + name + "', " +
                "Host Name = '"+ host + ", " +
                "Rating = " + rating + ", " +
                "Review Number = " + reviewCount + ", " +
                "Location = " + location +", "+
                "Price = " + price + "}";
    }
}

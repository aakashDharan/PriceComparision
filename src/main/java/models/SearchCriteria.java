package models;

import java.time.LocalDate;

public class SearchCriteria {
    private final String actualDestination;
    //private final LocalDate checkInDate;
    //private final LocalDate checkOutDate;
    private final String searchTxt;
    private final int guestNum;


    public SearchCriteria(String searchTxt, String destination, int guestNum) {
        this.actualDestination = destination;
        //this.checkInDate = checkInDate;
        //this.checkOutDate = checkOutDate;
        this.searchTxt = searchTxt;
        this.guestNum = guestNum;
    }

    public String getDestination() {
        return actualDestination;
    }

//    public LocalDate getCheckInDate() {
//        return checkInDate;
//    }

//    public LocalDate getCheckOutDate() {
//        return checkOutDate;
//    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public int getGuestNum() {
        return guestNum;
    }
}

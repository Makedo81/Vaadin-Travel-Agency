package com.individualproject.travel_agency.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingDto {

    private String firstname;
    private String lastname;
    private String hotelName;
    private int price;
    private int mealPrice;
    private int totalPrice;
    private String mealType;
    private String bookingCode;

    public BookingDto() {
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }
}



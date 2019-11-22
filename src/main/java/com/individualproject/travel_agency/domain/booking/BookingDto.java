package com.individualproject.travel_agency.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}



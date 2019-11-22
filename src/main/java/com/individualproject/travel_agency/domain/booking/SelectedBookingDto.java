package com.individualproject.travel_agency.domain.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedBookingDto {

    private String hotelName;
    private String city;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int priceExcludingMeal;
    private MealDto mealDto;
    private String loginStatus;
    private int totalBookingPrice;
}

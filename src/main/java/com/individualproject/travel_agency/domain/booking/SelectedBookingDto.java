package com.individualproject.travel_agency.domain.booking;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public int getPriceExcludingMeal() {
        return priceExcludingMeal;
    }

    public void setPriceExcludingMeal(int priceExcludingMeal) {
        this.priceExcludingMeal = priceExcludingMeal;
    }

    public MealDto getMealDto() {
        return mealDto;
    }

    public void setMealDto(MealDto mealDto) {
        this.mealDto = mealDto;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getTotalBookingPrice() {
        return totalBookingPrice;
    }

    public void setTotalBookingPrice(int totalBookingPrice) {
        this.totalBookingPrice = totalBookingPrice;
    }
}

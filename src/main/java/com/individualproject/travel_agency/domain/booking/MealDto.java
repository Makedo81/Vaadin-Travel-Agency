package com.individualproject.travel_agency.domain.booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto {

    private String mealType;
    private int price;

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MealDto(String mealType, int price) {
        this.mealType = mealType;
        this.price = price;
    }

    public MealDto() {
    }
}

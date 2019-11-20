package com.individualproject.travel_agency.domain.booking;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    private Long totalPrice;

    public Long getTotalPrice() {
        return totalPrice;
    }
}

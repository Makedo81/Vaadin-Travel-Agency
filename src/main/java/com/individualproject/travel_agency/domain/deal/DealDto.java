package com.individualproject.travel_agency.domain.deal;

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
public class DealDto {

    private String hotelName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int price;
}

package com.individualproject.travel_agency.domain.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealSearchDto {

    private String country;
    private String city;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
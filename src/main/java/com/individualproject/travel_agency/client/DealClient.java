package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.client.notifications.DealLayoutNotificationProcessor;
import com.individualproject.travel_agency.domain.booking.MealDto;
import com.individualproject.travel_agency.domain.booking.PriceDto;
import com.individualproject.travel_agency.domain.deal.DealDto;
import com.individualproject.travel_agency.domain.deal.layout.DealLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import static java.util.Optional.ofNullable;

@Component
public class DealClient {

    private RestTemplate restTemplate = new RestTemplate();
    private DealLayoutNotificationProcessor dealLayoutNotificationProcessor = new DealLayoutNotificationProcessor();

    public List<DealDto> getDeal(DealLayout dealLayout) throws DateTimeParseException {
        dealLayout.getDealsGrid().setVisible(true);
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/deal/get")
                .queryParam("country", dealLayout.getCountryCombo().getValue())
                .queryParam("city", dealLayout.getCityCombo().getValue())
                .queryParam("dateFrom", dealLayout.getEndDate().getMin().minusDays(1).toString())
                .queryParam("dateTo", dealLayout.getStartDate().getMax().plusDays(1).toString())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<DealDto[]> responseEntity = restTemplate.getForEntity(url, DealDto[].class);
        DealDto[] objects = responseEntity.getBody();
        dealLayoutNotificationProcessor.checkInputInDealLayout(dealLayout, objects);
        List<DealDto> deals = Arrays.asList(ofNullable(objects).orElse(new DealDto[0]));
        return deals;
    }

    public List<MealDto> getMealPrices(DealLayout dealLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/meals/getMealPrices")
                .queryParam("hotelName", dealLayout.getSelectedDeal().getHotelName())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<MealDto[]> responseEntity = restTemplate.getForEntity(url, MealDto[].class);
        MealDto[] objects = responseEntity.getBody();
        List<MealDto> mealDtoList = Arrays.asList(ofNullable(objects).orElse(new MealDto[0]));
        return mealDtoList;
    }

    public PriceDto getDealPrice(DealLayout dealLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/deal/getPrice")
                .queryParam("dateFrom", dealLayout.getSelectedDeal().getDateFrom())
                .queryParam("dateTo", dealLayout.getSelectedDeal().getDateTo())
                .queryParam("cityName", dealLayout.getCityCombo().getValue())
                .queryParam("mealPrice", dealLayout.getMeal().getPrice())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<PriceDto> responseEntity = restTemplate.getForEntity(url, PriceDto.class);
        PriceDto objects = responseEntity.getBody();
        return objects;
    }
}



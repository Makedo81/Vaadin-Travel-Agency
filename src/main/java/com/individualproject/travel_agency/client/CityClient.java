package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.domain.city.CityDto;
import com.individualproject.travel_agency.domain.deal.layout.DealLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import static java.util.Optional.ofNullable;

@Component
public class CityClient {

    private RestTemplate restTemplate = new RestTemplate();

    public List<CityDto> getCity(DealLayout dealLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/cities/get")
                .queryParam("country", dealLayout.getCountryCombo().getValue())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<CityDto[]> responseEntity = restTemplate.getForEntity(url, CityDto[].class);
        CityDto[] objects = responseEntity.getBody();
        List<CityDto> resultList = Arrays.asList(ofNullable(objects).orElse(new CityDto[0]));
        return resultList;
    }
}

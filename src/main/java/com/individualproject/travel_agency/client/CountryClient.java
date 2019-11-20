package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.domain.country.CountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class CountryClient {

    private RestTemplate restTemplate = new RestTemplate();

    public List<CountryDto> getCountry() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/countries/get")
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<CountryDto[]> responseEntity = restTemplate.getForEntity(url, CountryDto[].class);
        CountryDto[] objects = responseEntity.getBody();
        List<CountryDto> countriesList = Arrays.asList(ofNullable(objects).orElse(new CountryDto[0]));
        return countriesList;
    }
}

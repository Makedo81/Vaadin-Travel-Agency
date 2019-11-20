package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.domain.weather.WeatherResponseDto;
import com.individualproject.travel_agency.domain.weather.layout.WeatherSearchLayout;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherForecastClient {

    private RestTemplate restTemplate = new RestTemplate();

    public WeatherResponseDto checkWeather(WeatherSearchLayout weatherSearchLayout) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.set("x-rapidapi-key", "a0122d75a6msh995563ef363e938p118352jsn6a77565a176c");
        URI url = UriComponentsBuilder.fromHttpUrl("https://community-open-weather-map.p.rapidapi.com/weather")
                .queryParam("q", weatherSearchLayout.getTypeCity().getValue())
                .build().encode().toUri();
        System.out.println(url);
        HttpEntity<String> entity = new HttpEntity(headers);
        System.out.println(entity);
        ResponseEntity<WeatherResponseDto> result = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherResponseDto.class);
        return result.getBody();
    }
}

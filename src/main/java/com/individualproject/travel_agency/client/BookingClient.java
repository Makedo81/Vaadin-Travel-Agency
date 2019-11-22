package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.client.notifications.BookingLayoutNotificationProcessor;
import com.individualproject.travel_agency.domain.booking.*;
import com.individualproject.travel_agency.domain.booking.layout.BookingLayout;
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
public class BookingClient {

    private RestTemplate restTemplate = new RestTemplate();
    private BookingLayoutNotificationProcessor bookingLayoutNotificationProcessor = new BookingLayoutNotificationProcessor();

    public SelectedBookingDto addDeal(final DealLayout dealLayout) {

        SelectedBookingDto selectedBookingDto = new SelectedBookingDto();
        selectedBookingDto.setHotelName(dealLayout.getSelectedDeal().getHotelName());
        selectedBookingDto.setCity(dealLayout.getCityCombo().getValue());
        selectedBookingDto.setPriceExcludingMeal(dealLayout.getSelectedDeal().getPrice());
        selectedBookingDto.setDateFrom(dealLayout.getSelectedDeal().getDateFrom());
        selectedBookingDto.setDateTo(dealLayout.getSelectedDeal().getDateTo());
        selectedBookingDto.setTotalBookingPrice(dealLayout.getPriceWithMeal().getTotalPrice().intValue());
        selectedBookingDto.setMealDto(
                new MealDto(
                        dealLayout.getMeal().getMealType(),
                        dealLayout.getMeal().getPrice()));
        selectedBookingDto.setLoginStatus(dealLayout.getLoginStatus().getText());
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/add")
                .queryParam("hotelName", selectedBookingDto.getHotelName())
                .queryParam("cityName", selectedBookingDto.getCity())
                .queryParam("price", selectedBookingDto.getPriceExcludingMeal())
                .queryParam("dateFrom", selectedBookingDto.getDateFrom())
                .queryParam("dateTo", selectedBookingDto.getDateTo())
                .queryParam("mealType", selectedBookingDto.getMealDto().getMealType())
                .queryParam("mealPrice", selectedBookingDto.getMealDto().getPrice())
                .queryParam("totalPrice", selectedBookingDto.getTotalBookingPrice())
                .queryParam("loginValue", selectedBookingDto.getLoginStatus())
                .build().encode().toUri();
        System.out.println(url);
        return restTemplate.postForObject(url, selectedBookingDto, SelectedBookingDto.class);
    }

    public List<BookingDto> getBooking(final BookingLayout bookingLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/getBooking")
                .queryParam("loginValue", bookingLayout.getLoginStatus().getText())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<BookingDto[]> responseEntity = restTemplate.getForEntity(url, BookingDto[].class);
        BookingDto[] objects = responseEntity.getBody();
        List<BookingDto> bookingsList = Arrays.asList(ofNullable(objects).orElse(new BookingDto[0]));
        return bookingsList;
    }

    public void deleteReservation(final BookingLayout bookingLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/delete")
                .queryParam("bookingCode", bookingLayout.getReservation().getBookingCode())
                .build().encode().toUri();
        System.out.println(url);
        restTemplate.delete(url);
    }

    public void payReservation(final BookingLayout bookingLayout) {
        if (bookingLayoutNotificationProcessor.checkFields(bookingLayout)) {
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/pay")
                    .queryParam("bookingCode", bookingLayout.getReservation().getBookingCode())
                    .queryParam("loginValue", bookingLayout.getLoginStatus().getText())
                    .queryParam("cardNumber", bookingLayout.getCardNumber().getValue())
                    .queryParam("cvcNumber", bookingLayout.getCardCVC().getValue())
                    .build().encode().toUri();
            System.out.println(url);
            restTemplate.put(url, bookingLayout.getClass());
            bookingLayoutNotificationProcessor.paymentSuccessful();
        }
    }

    public PaymentStatusDto getPaymentStatus(final BookingLayout bookingLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/getStatus")
                .queryParam("bookingCode", bookingLayout.getReservation().getBookingCode())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<PaymentStatusDto> responseEntity = restTemplate.getForEntity(url, PaymentStatusDto.class);
        PaymentStatusDto objects = responseEntity.getBody();
        System.out.println(objects);
        return objects;
    }

    public FinalPriceDto getPrice(final BookingLayout bookingLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/getFinalPrice")
                .queryParam("bookingCode", bookingLayout.getReservation().getBookingCode())
                .build().encode().toUri();
        System.out.println(url);
        ResponseEntity<FinalPriceDto> responseEntity = restTemplate.getForEntity(url, FinalPriceDto.class);
        FinalPriceDto reservationFinalPrice = responseEntity.getBody();
        return reservationFinalPrice;
    }

    public void update(final BookingLayout bookingLayout) {
        if (bookingLayoutNotificationProcessor.check(bookingLayout)) {
            String valueToUpdate = bookingLayout.getMenuItems().getValue();
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/bookings/update")
                    .queryParam("bookingCode", bookingLayout.getReservation().getBookingCode())
                    .queryParam("valueToUpdate", valueToUpdate)
                    .build().encode().toUri();
            System.out.println(url);
            restTemplate.put(url, bookingLayout.getClass());
        }
    }
}




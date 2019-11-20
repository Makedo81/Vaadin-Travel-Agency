package com.individualproject.travel_agency.domain.booking.layout;

import com.individualproject.travel_agency.client.BookingClient;
import com.individualproject.travel_agency.domain.booking.BookingDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingLayoytStylesSetter {

    private BookingLayout bookingLayout;

    public void setStyles(boolean status,
                          Grid<BookingDto> booking,
                          VerticalLayout bookingResults,
                          Label signInInfoReminder,
                          BookingLayout bookingLayout) {

        BookingClient bookingClient = new BookingClient();
        if (status) {
            bookingResults.remove(signInInfoReminder);
            bookingResults.add(booking);
            booking.setMaxHeight("200px");
            booking.setWidth("500px");
            booking.setColumns(
                    "bookingCode",
                    "firstname",
                    "lastname",
                    "hotelName",
                    "mealType",
                    "price",
                    "mealPrice",
                    "totalPrice");
            booking.setItems(bookingClient.getBooking(bookingLayout));
        }
    }
}


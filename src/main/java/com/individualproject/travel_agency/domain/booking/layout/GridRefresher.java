package com.individualproject.travel_agency.domain.booking.layout;

import com.individualproject.travel_agency.client.BookingClient;

public class GridRefresher {

    public void updateView(BookingLayout bookingLayout) {
        BookingClient bookingClient = new BookingClient();
        bookingClient.getBooking(bookingLayout);
        bookingLayout.getBooking().setItems(bookingClient.getBooking(bookingLayout));
        bookingLayout.getFieldPrice().setValue("");
    }
}

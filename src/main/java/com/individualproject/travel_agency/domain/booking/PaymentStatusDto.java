package com.individualproject.travel_agency.domain.booking;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusDto {

    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }
}

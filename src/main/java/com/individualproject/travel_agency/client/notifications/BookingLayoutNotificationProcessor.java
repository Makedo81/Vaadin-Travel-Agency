package com.individualproject.travel_agency.client.notifications;

import com.individualproject.travel_agency.domain.booking.layout.BookingLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingLayoutNotificationProcessor {

    private Notification notificationPayment = new Notification("Please add card details");
    private Notification alreadyPaidNotification = new Notification("Already paid");
    private Notification incorrectCardFormatNotification = new Notification(" Format incorrect");
    private Notification paymentSuccesfulNotification = new Notification(" Payment succesfully received");

    public boolean checkFields(BookingLayout bookingLayout) {
        if (!bookingLayout.paymentStatus()) {
            alreadyPaidNotification.setPosition(Notification.Position.BOTTOM_END);
            alreadyPaidNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            alreadyPaidNotification.open();
            alreadyPaidNotification.setDuration(1800);
            return false;
        } else if(checkEmptyFields(bookingLayout)); {
        return true;
        }
    }

    public boolean check (BookingLayout bookingLayout){
            if (bookingLayout.paymentStatus()) {
                alreadyPaidNotification.close();
                return true;
            } else
                alreadyPaidNotification.setPosition(Notification.Position.BOTTOM_END);
                alreadyPaidNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                alreadyPaidNotification.open();
                alreadyPaidNotification.setDuration(1800);
            return false;
        }

        public void paymentSuccessful () {
            paymentSuccesfulNotification.setPosition(Notification.Position.TOP_STRETCH);
            paymentSuccesfulNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            paymentSuccesfulNotification.open();
            paymentSuccesfulNotification.setDuration(1800);
        }

        public boolean checkEmptyFields (BookingLayout bookingLayout){
            alreadyPaidNotification.close();
            if(bookingLayout.getCardNumber().getValue().equals("") && (bookingLayout.getCardCVC().getValue().equals(""))
                ||
                    (bookingLayout.getCardNumber().getValue().equals("") && (!bookingLayout.getCardCVC().getValue().equals("")))
                    ||
                    !bookingLayout.getCardNumber().getValue().equals("") && (bookingLayout.getCardCVC().getValue().equals(""))) {
                notificationPayment.setPosition(Notification.Position.BOTTOM_END);
                notificationPayment.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                notificationPayment.open();
                notificationPayment.setDuration(2160);
                return false;
            } else
                notificationPayment.close();
                return checkIfInputCorrect(bookingLayout);
        }

    public boolean checkIfInputCorrect(BookingLayout bookingLayout) {
            notificationPayment.close();

        if ((bookingLayout.getCardNumber().getValue().equals("")
                ||
                bookingLayout.getCardNumber().getValue().matches("[a-zA-Z_]")
                ||
                bookingLayout.getCardCVC().getValue().equals(""))
                ||
                bookingLayout.getCardCVC().getValue().length() != 3
                ||
                bookingLayout.getCardCVC().getValue().matches("[a-zA-Z_]")) {
            incorrectCardFormatNotification.setPosition(Notification.Position.BOTTOM_END);
            incorrectCardFormatNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            incorrectCardFormatNotification.open();
            incorrectCardFormatNotification.setDuration(2160);
            return false;
        } else return true;
    }
}
//
//    private Notification notificationPayment = new Notification("Please add card details");
//    private Notification alreadyPaidNotification = new Notification("Already paid");
//    private Notification incorrectCardFormatNotification = new Notification(" Format incorrect");
//    private Notification paymentSuccesfulNotification = new Notification(" Payment succesfully received");
//
//    public boolean checkFields(BookingLayout bookingLayout) {
//        if (bookingLayout.paymentStatus()) {
//            alreadyPaidNotification.close();
//            if ((bookingLayout.getCardNumber().getValue().equals("") && (bookingLayout.getCardNumber().getValue().equals(""))
//            if ((bookingLayout.getCardNumber().getValue().equals("")
//                    ||
//                    bookingLayout.getCardNumber().getValue().matches("[a-zA-Z_]")
//                    ||
//                    bookingLayout.getCardCVC().getValue().equals(""))
//                    ||
//                    bookingLayout.getCardCVC().getValue().length()!=3
//                    ||
//                    bookingLayout.getCardCVC().getValue().matches("[a-zA-Z_]")) {
//                notificationPayment.setPosition(Notification.Position.BOTTOM_END);
//                notificationPayment.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
//                notificationPayment.open();
//                notificationPayment.setDuration(2160);
//                return false;
//            } else
//                incorrectCardFormatNotification.close();
//            return true;
//        } else
//            incorrectCardFormatNotification.close();
//        alreadyPaidNotification.setPosition(Notification.Position.BOTTOM_END);
//        alreadyPaidNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//        alreadyPaidNotification.open();
//        alreadyPaidNotification.setDuration(1800);
//        return false;
//    }
//
//    public boolean check(BookingLayout bookingLayout) {
//        if (bookingLayout.paymentStatus()) {
//            alreadyPaidNotification.close();
//            return true;
//        } else
//            alreadyPaidNotification.setPosition(Notification.Position.BOTTOM_END);
//        alreadyPaidNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//        alreadyPaidNotification.open();
//        alreadyPaidNotification.setDuration(1800);
//        return false;
//    }
//
//    public void paymentSuccessful() {
//        paymentSuccesfulNotification.setPosition(Notification.Position.TOP_STRETCH);
//        paymentSuccesfulNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
//        paymentSuccesfulNotification.open();
//        paymentSuccesfulNotification.setDuration(1800);
//    }
//}

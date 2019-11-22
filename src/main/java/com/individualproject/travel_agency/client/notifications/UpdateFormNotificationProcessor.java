package com.individualproject.travel_agency.client.notifications;

import com.vaadin.flow.component.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFormNotificationProcessor {

    private Notification incorrectFormatNotification = new Notification(" Format incorrect");

    public boolean checkInputUpdateForm(String objectToUpdate, String valueToUpdate) {
        switch (objectToUpdate) {
            case "LASTNAME":
                if (valueToUpdate.equals("") ||
                        valueToUpdate.matches("[0-9]+") ||
                        (valueToUpdate.matches(".*[0-9].*") && valueToUpdate.matches(".*[a-zA-Z].*"))) {
                    incorrectFormatNotification.open();
                    incorrectFormatNotification.setDuration(1800);
                    return false;
                } else incorrectFormatNotification.close();
                break;
            case "EMAIL":
                if (valueToUpdate.equals("") || !valueToUpdate.contains("@")) {
                    incorrectFormatNotification.open();
                    incorrectFormatNotification.setDuration(1800);
                    return false;
                } else incorrectFormatNotification.close();
                incorrectFormatNotification.setDuration(1800);
                break;
            case "PHONE NUMBER":
                if (valueToUpdate.equals("") || valueToUpdate.matches("[a-zA-Z_]") ||
                        !valueToUpdate.matches("[0-9]+") || valueToUpdate.length() < 9 || valueToUpdate.length()> 12
                        ) {
                    incorrectFormatNotification.open();
                    return false;
                } else incorrectFormatNotification.close();
                break;
        }
        return true;
    }
}

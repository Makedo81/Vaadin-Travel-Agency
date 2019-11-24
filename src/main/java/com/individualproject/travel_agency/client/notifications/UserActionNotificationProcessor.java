package com.individualproject.travel_agency.client.notifications;

import com.individualproject.travel_agency.domain.user.layout.RegistrationFormLayout;
import com.individualproject.travel_agency.domain.user.layout.SignInFormLayout;
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
public class UserActionNotificationProcessor {

    private Notification incorrectSignUpDataNotification = new Notification("Invalid login or password");
    private Notification incorrectFormatNotification = new Notification(" Format incorrect");


    public void checkInputSignUpForm(SignInFormLayout signInFormLayout) {
        incorrectSignUpDataNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        if ((signInFormLayout.getLogin().getValue().equals("") && signInFormLayout.getPasswordField().getValue() != (""))
                || (signInFormLayout.getPasswordField().getValue().equals("") && signInFormLayout.getLogin().getValue() != (""))) {
            incorrectSignUpDataNotification.open();
            incorrectSignUpDataNotification.setDuration(3600);
        } else incorrectSignUpDataNotification.close();
    }

    public boolean checkInputRegistrationForm(RegistrationFormLayout registrationFormLayout) {
        incorrectFormatNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        if ((registrationFormLayout.getPhone().getValue().equals("")
                ||
                !registrationFormLayout.getPhone().getValue().matches("[0-9]+")
                ||
                registrationFormLayout.getPhone().getValue().length() < 9 || registrationFormLayout.getPhone().getValue().length() > 12)
                ||
                (registrationFormLayout.getPassword().getValue().equals("")
                        ||
                        registrationFormLayout.getLogin().getValue().equals("")
                        ||
                        registrationFormLayout.getName().getValue().equals("")
                        ||
                        registrationFormLayout.getName().getValue().matches(".*[0-9].*") && registrationFormLayout.getName().getValue().matches(".*[a-zA-Z].*")
                        ||
                        registrationFormLayout.getName().getValue().matches("[0-9]+")
                        ||
                        registrationFormLayout.getSurname().getValue().equals("")
                        ||
                        registrationFormLayout.getSurname().getValue().matches(".*[0-9].*") && registrationFormLayout.getSurname().getValue().matches(".*[a-zA-Z].*")
                        ||
                        registrationFormLayout.getSurname().getValue().matches("[0-9]+")
                        ||
                        registrationFormLayout.getEmail().getValue().equals(""))
                ||
                !registrationFormLayout.getEmail().getValue().contains("@")) {
            incorrectFormatNotification.open();
            incorrectFormatNotification.setDuration(1800);
            return false;
        } else
            incorrectFormatNotification.close();
        return true;
    }
}

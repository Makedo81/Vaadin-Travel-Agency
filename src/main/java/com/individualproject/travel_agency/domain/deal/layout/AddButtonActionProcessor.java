package com.individualproject.travel_agency.domain.deal.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.BookingClient;
import com.individualproject.travel_agency.domain.deal.DealDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.time.temporal.ChronoUnit.DAYS;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddButtonActionProcessor {

    private DealLayout dealLayout;

    public void setMessageLabelText(DealDto selectedDeal, Label notificationMessage) {

        long period = DAYS.between(selectedDeal.getDateFrom(), selectedDeal.getDateTo()) + 1;
        notificationMessage.setText(
                "YOU CHOSE STAY IN HOTEL "
                        + selectedDeal.getHotelName().toUpperCase()
                        + "  FOR "
                        + period
                        + " DAYS. "
                        + "\n"
                        + "* all prices do not include meals - please select your meal");
    }

    public void addDeals(DealLayout dealLayout, MainView mainView, Label loginStatus, Button addButton) {
        BookingClient bookingClient = new BookingClient();
        addButton.addClickListener(event2 -> {
            if (status(mainView, loginStatus)) {
                bookingClient.addDeal(dealLayout);

                dealLayout.getMealResultLayout().getElement().removeAllChildren();
                dealLayout.getEndDate().clear();
                dealLayout.getStartDate().clear();
                dealLayout.getCountryCombo().clear();
                dealLayout.getCityCombo().clear();
                dealLayout.getNotificationMessage().setVisible(false);
                dealLayout.getSearchingForm().remove(dealLayout.getPrice(), dealLayout.getDealsGrid());
            }
        });
    }

    private boolean status(MainView mainView, Label loginStatus) {
        loginStatus.setText(mainView.getSignInFormLayout().getLoginInfo().getText());
        boolean isSignOut = loginStatus.getText().equals("");
        if (isSignOut) {
            return false;
        } else return true;
    }
}

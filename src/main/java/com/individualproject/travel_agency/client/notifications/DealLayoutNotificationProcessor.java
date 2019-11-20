package com.individualproject.travel_agency.client.notifications;

import com.individualproject.travel_agency.domain.deal.DealDto;
import com.individualproject.travel_agency.domain.deal.layout.DealLayout;
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
public class DealLayoutNotificationProcessor {

    private Notification noDealsAvailableNotification = new Notification("No deals available in this time");

    public void checkInputInDealLayout(DealLayout dealLayout, DealDto[] objects) {
        if (objects.length == 0) {
            dealLayout.getDealsGrid().setVisible(false);
            noDealsAvailableNotification.setPosition(Notification.Position.TOP_STRETCH);
            noDealsAvailableNotification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            noDealsAvailableNotification.open();
            noDealsAvailableNotification.setDuration(3600);
        } else noDealsAvailableNotification.close();
    }
}

package com.individualproject.travel_agency.domain.deal.layout;

import com.vaadin.flow.component.html.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatePickerCreator {

    private DealLayout dealLayout;

    /**
     * *  DatePicker code borrowed from Vaadin examples
     */

    public void searchDate(DealLayout dealLayout) {

        dealLayout.getStartDate().addValueChangeListener(event ->
        {
            LocalDate selectedDate = event.getValue();
            LocalDate endDate = dealLayout.getEndDate().getValue();
            if (selectedDate != null) {
                System.out.println("selected date " + selectedDate);
                dealLayout.getEndDate().setMin(selectedDate.plusDays(1));
                if (endDate == null) {
                    dealLayout.getEndDate().setOpened(true);
                    dealLayout.getMessage().setText("Select the ending date");
                } else {
                    dealLayout.getMessage().setText(
                            "Selected period:\nFrom " + selectedDate.toString()
                                    + " to " + endDate.toString());
                }
            } else {
                dealLayout.getEndDate().setMin(null);
                dealLayout.getMessage().setText("Select the starting date");
            }
        });

        dealLayout.getEndDate().addValueChangeListener(event -> {
            LocalDate selectedDate2 = event.getValue();
            LocalDate startDate = dealLayout.getStartDate().getValue();
            if (selectedDate2 != null) {
                dealLayout.getStartDate().setMax(selectedDate2.minusDays(1));
                if (startDate != null) {
                    dealLayout.getMessage().setText(
                            "Selected period:\nFrom " + startDate.toString()
                                    + " to " + selectedDate2.toString());
                } else {
                    dealLayout.getMessage().setText("Select the starting date");
                }
            } else {
                dealLayout.getStartDate().setMax(null);
                if (startDate != null) {
                    dealLayout.getMessage().setText("Select the ending date");
                } else {
                    dealLayout.getMessage().setText("No date is selected");
                }
            }
        });
    }
}

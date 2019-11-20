package com.individualproject.travel_agency.domain.deal.layout;

import com.individualproject.travel_agency.domain.booking.MealDto;
import com.individualproject.travel_agency.domain.deal.DealDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
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
public class StyleSetters {

    private DealLayout dealLayout;

    public void setNotificationStyle(DealLayout dealLayout) {

        dealLayout.getNotificationMessage().setHeight("60px");
        dealLayout.getNotificationMessage().setWidth("480px");
        dealLayout.getNotificationMessage().getStyle().set("border", "3px solid #9E9E9E");
        dealLayout.getNotificationMessage().getStyle().set("color", "red");
        dealLayout.getNotificationMessage().getStyle().set("text-align", "center");
        dealLayout.getNotificationMessage().getStyle().set("background-color", "yellow");
    }

    public void setFieldsStyle(Grid<DealDto> dealsGrid, Grid<MealDto> mealPricesGrid, VerticalLayout searchingForm) {

        searchingForm.setWidth("500px");
        searchingForm.setSizeUndefined();

        dealsGrid.setWidthFull();
        dealsGrid.setWidth("480px");
        dealsGrid.setHeight("200px");
        dealsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        dealsGrid.setColumns("hotelName", "dateFrom", "dateTo", "price");

        mealPricesGrid.setColumns("mealType", "price");
        mealPricesGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT, GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_COMPACT, GridVariant.LUMO_COLUMN_BORDERS);
        mealPricesGrid.setHeight("200px");
        mealPricesGrid.setWidth("280px");
    }

    public void settingStyles(DatePicker endDate, DatePicker startDate,
                              ComboBox<String> cityCombo, ComboBox<String> countryCombo,
                              Button addButton, Button searchButton,
                              Label price) {
        countryCombo.setWidth("150px");
        startDate.setWidth("150px");
        endDate.setWidth("150px");
        cityCombo.setWidth("150px");
        price.setWidth("80px");
        searchButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        addButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
    }
}

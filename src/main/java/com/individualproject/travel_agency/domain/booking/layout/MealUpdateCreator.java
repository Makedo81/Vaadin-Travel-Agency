package com.individualproject.travel_agency.domain.booking.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.BookingClient;
import com.individualproject.travel_agency.domain.deal.Meal;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealUpdateCreator {

    private BookingLayout bookingLayout;

    public void createUpdateMenu(BookingLayout bookingLayout, MenuBar bar) {

        MenuItem update = bar.addItem("Update");
        SubMenu meals = update.getSubMenu();
        MenuItem menu = meals.addItem("meal to:");
        SubMenu subMenu = menu.getSubMenu();
        subMenu.addItem(String.valueOf(Meal.ONLY_DINNER)).addClickListener(p -> {
            bookingLayout.getMenuItems().setValue(String.valueOf(Meal.ONLY_DINNER));
            updateView(bookingLayout);
        });
        subMenu.addItem(String.valueOf(Meal.ONLY_BREAKFAST)).addClickListener(p -> {
            bookingLayout.getMenuItems().setValue(String.valueOf(Meal.ONLY_BREAKFAST));
            updateView(bookingLayout);

        });
        subMenu.addItem(String.valueOf(Meal.BREAKFAST_LUNCH_DINNER)).addClickListener(p -> {
            bookingLayout.getMenuItems().setValue(String.valueOf(Meal.BREAKFAST_LUNCH_DINNER));
            updateView(bookingLayout);

        });
        subMenu.addItem(String.valueOf(Meal.NO_MEAL)).addClickListener(p -> {
            bookingLayout.getMenuItems().setValue(String.valueOf(Meal.NO_MEAL));
            updateView(bookingLayout);
        });
    }

    public boolean status(BookingLayout bookingLayout, MainView mainView) {
        bookingLayout.getLoginStatus().setText(mainView.getSignInFormLayout().getLoginInfo().getText());
        boolean isSignOut = bookingLayout.getLoginStatus().getText().equals("");
        if (isSignOut) {
            return false;
        } else return true;
    }

    public void updateView(BookingLayout bookingLayout) {
        BookingClient bookingClient = new BookingClient();
        bookingClient.update(bookingLayout);
        bookingLayout.getBooking().setItems(bookingClient.getBooking(bookingLayout));
        bookingLayout.getFieldPrice().setValue(bookingClient.getPrice(bookingLayout).getFinalPriceDto().toString() + " euro");
    }
}

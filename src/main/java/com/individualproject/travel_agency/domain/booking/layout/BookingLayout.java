package com.individualproject.travel_agency.domain.booking.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.BookingClient;
import com.individualproject.travel_agency.client.DealClient;
import com.individualproject.travel_agency.client.UserClient;
import com.individualproject.travel_agency.domain.booking.BookingDto;
import com.individualproject.travel_agency.domain.booking.FinalPriceDto;
import com.individualproject.travel_agency.domain.booking.PaymentStatusDto;
import com.individualproject.travel_agency.domain.deal.Meal;
import com.individualproject.travel_agency.domain.user.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingLayout {

    private MainView mainView;
    private UserDto userDto;
    private Meal meal;
    private BookingDto reservation;
    private BookingClient bookingClient = new BookingClient();
    private UserClient userClient = new UserClient();
    private DealClient dealClient = new DealClient();
    private HorizontalLayout actionDeal = new HorizontalLayout();
    private VerticalLayout title = new VerticalLayout();
    private VerticalLayout bookingResults = new VerticalLayout();
    private VerticalLayout paymentLayout = new VerticalLayout();
    private HorizontalLayout priceInfo = new HorizontalLayout();
    private Grid<BookingDto> booking = new Grid<>(BookingDto.class);
    private Label signInInfoReminder = new Label();
    private Label loginStatus = new Label();
    private Label info = new Label();
    private TextField fieldPrice = new TextField();
    private TextField cardNumber = new TextField("Card number");
    private TextField cardCVC = new TextField("CVC");
    private TextField menuItems = new TextField();
    private Button showBookingsButton = new Button("Show booking");
    private Button deleteBooking = new Button("Remove booking");
    private Button payButton = new Button("Pay");
    private MenuBar bar = new MenuBar();
    private Select<String> select = new Select<>();
    private MealUpdateCreator mealUpdateCreator = new MealUpdateCreator();
    private BookingLayoytStylesSetter bookingLayoytStylesSetter = new BookingLayoytStylesSetter();

    public BookingLayout(MainView mainView) {
        this.mainView = mainView;
        loginStatus.setText(mainView.getSignInFormLayout().getLoginInfo().getText());
        bar.setOpenOnHover(true);
        booking.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
        payButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        actionDeal.add(showBookingsButton, deleteBooking, bar, payButton);
        mealUpdateCreator.createUpdateMenu(this, bar);

        showBookingsButton.addClickListener(event -> {
            boolean status = mealUpdateCreator.status(this, mainView);
            bookingLayoytStylesSetter.setStyles(status, booking, bookingResults, signInInfoReminder, this);
        });

        deleteBooking.addClickListener(event -> bookingClient.deleteReservation(this));

        booking.addItemClickListener(event -> {
            paymentLayout.remove(cardNumber, cardCVC);
            reservation = new BookingDto();
            reservation.setBookingCode(event.getItem().getBookingCode());
            fieldPrice.setWidth("100px");
            info.setText("Total price for selected deal: ");
            priceInfo.add(info, fieldPrice);
            FinalPriceDto p = bookingClient.getPrice(this);
            fieldPrice.setValue(p.getFinalPriceDto().toString() + " euro");
        });

        payButton.addClickListener(event -> {
            bookingClient.getPrice(this);
            paymentLayout.add(cardNumber, cardCVC);
            cardCVC.setPlaceholder("ie.856");
            bookingClient.payReservation(this);
            bookingClient.getPaymentStatus(this);
            if (bookingClient.getPaymentStatus(this).getPaymentStatus().equals("closed")) {
                cardNumber.clear();
                cardCVC.clear();
                paymentLayout.remove(cardNumber, cardCVC);
            }
        });
    }

    public boolean paymentStatus() {
        PaymentStatusDto paymentInfo = bookingClient.getPaymentStatus(this);
        String paymentStatus = paymentInfo.getPaymentStatus();
        return paymentStatus.equals("open");
    }
}

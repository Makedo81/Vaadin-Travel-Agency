package com.individualproject.travel_agency;

import com.individualproject.travel_agency.domain.booking.layout.BookingLayout;
import com.individualproject.travel_agency.domain.deal.layout.DealLayout;
import com.individualproject.travel_agency.domain.user.layout.RegistrationFormLayout;
import com.individualproject.travel_agency.domain.user.layout.SignInFormLayout;
import com.individualproject.travel_agency.domain.user.layout.UpdateFormLayout;
import com.individualproject.travel_agency.domain.weather.layout.WeatherSearchLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Route(value = "TravelAgency")
public class MainView extends HorizontalLayout {

    private SignInFormLayout signInFormLayout = new SignInFormLayout(this);
    private DealLayout dealLayout = new DealLayout(this);
    private BookingLayout invoice = new BookingLayout(this);
    private Accordion accordion = new Accordion();

    public MainView() {
        accordion.close();
        VerticalLayout panelOne = new VerticalLayout();
        VerticalLayout panelTwo = new VerticalLayout();
        VerticalLayout panelThree = new VerticalLayout();
        HorizontalLayout panelFive = new HorizontalLayout();
        HorizontalLayout panelSix = new HorizontalLayout();
        UpdateFormLayout updateFormLayout = new UpdateFormLayout(this);
        WeatherSearchLayout weatherSearchLayout = new WeatherSearchLayout(this);
        RegistrationFormLayout registrationFormLayout = new RegistrationFormLayout(this);

        accordion.add("Registration Form", registrationFormLayout.getCreateUserLayout());
        accordion.add("Update Form", updateFormLayout.getUpdateFormLayout());
        accordion.add("Account info", signInFormLayout.getAccountInfo());

        Label mainHeader = new Label("   TRAVEL - AGENCY   ");
        mainHeader.getStyle().set("background-color", "#DCDCDC");
        mainHeader.getStyle().set("text-align", "center");

        panelOne.add(signInFormLayout.getLoginLayout(), signInFormLayout.getSignAction());
        panelOne.add(accordion, signInFormLayout.getAdditionalStatusLayout(), signInFormLayout.getStatusLayout());
        panelOne.setMaxWidth("230px");
        panelOne.setHeight("1000");

        HorizontalLayout p1 = new HorizontalLayout();
        p1.getStyle().set("border", "3px solid #9E9E9E");
        panelTwo.setWidth("500");
        panelTwo.setHeight("200");
        panelOne.getStyle().set("background", "#FFF0F5");
        panelSix.getStyle().set("border", "1px solid #9E9E9E");
        panelFive.getStyle().set("border", "1px solid #9E9E9E");

        mainHeader.getStyle().set("colour", "green");
        mainHeader.getStyle().set("font-size", "40px");
        mainHeader.setWidth("520px");
        panelTwo.add(
                mainHeader,
                dealLayout.getSearchingForm(),
                dealLayout.getInfoLayout(),
                dealLayout.getActionLayout());

        panelThree.add(
                weatherSearchLayout.getWeatherForecastLayout(),
                invoice.getTitle(),
                invoice.getActionDeal(),
                invoice.getBookingResults(),
                invoice.getPriceInfo(),
                invoice.getPaymentLayout(),
                dealLayout.getHotelsResultLayout()
        );
        add(panelOne, panelTwo, panelThree);
    }

    public void check() {
        invoice.getBookingResults().removeAll();
        invoice.getPaymentLayout().removeAll();
        invoice.getPriceInfo().removeAll();
        accordion.getElement().getChild(0).setVisible(true);
        dealLayout.getHotelsResultLayout().removeAll();
        dealLayout.getInfoLayout().removeAll();
        dealLayout.getMealResultLayout().removeAll();
        accordion.close();
    }
}


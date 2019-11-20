package com.individualproject.travel_agency.domain.user.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.UserClient;
import com.individualproject.travel_agency.domain.booking.layout.BookingLayout;
import com.individualproject.travel_agency.domain.user.UserDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInFormLayout extends UI {

    private MainView mainView;
    private UserClient userClient = new UserClient();
    private Label statusDescription = new Label();
    private Label loginInfo = new Label();
    private Label statusDescription1 = new Label();
    private Button signIn = new Button("Sign in");
    private Button signOut = new Button("Sign out");
    private Button accountInfoButton = new Button("Get account info");
    private FormLayout loginLayout = new FormLayout();
    private VerticalLayout accountInfo = new VerticalLayout();
    private VerticalLayout statusLayout = new VerticalLayout();
    private HorizontalLayout signAction = new HorizontalLayout();
    private VerticalLayout additionalStatusLayout = new VerticalLayout();
    private VerticalLayout personalInfo = new VerticalLayout();
    private TextField login = new TextField("LOGIN:");
    private TextField statusMessage = new TextField();
    private TextField name = new TextField("NAME");
    private TextField lastname = new TextField("SURNAME");
    private TextField email = new TextField("EMAIL");
    private TextField contact = new TextField("PHONE NUMBER");
    private PasswordField passwordField = new PasswordField();
    private BookingLayout bookingLayout;

    public SignInFormLayout(MainView mainView) {
        this.mainView = mainView;

        signIn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        login.setWidth("150px");
        passwordField.setWidth("150px");
        passwordField.setLabel("PASSWORD");
        loginLayout = new FormLayout(login, passwordField);
        signAction = new HorizontalLayout(signIn, signOut);
        statusLayout = new VerticalLayout(statusDescription1);
        additionalStatusLayout = new VerticalLayout(statusDescription);

        signIn.addClickListener(event -> {
            userClient.signUp(this);
            statusDescription.setText(login.getValue());
            statusDescription.setVisible(false);
            if (!login.getValue().isEmpty() && !passwordField.isEmpty()) {
                loginInfo.setText(login.getValue());
                statusDescription1.setText(login.getValue().toUpperCase() + "" + " is logged in");
            }
            login.clear();
            passwordField.clear();
            mainView.remove();
            removeRegistrationForm();
            personalInfo = new VerticalLayout(name, lastname, contact, email);
            mainView.getAccordion().addOpenedChangeListener(e -> showAccountInfo());
        });

        signOut.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
        signOut.addClickListener(event -> {
            userClient.signOut(this);
            loginInfo.setText(login.getValue());
            statusDescription1.setText("User is logged out");
            statusDescription.setText("");
            removeRegistrationForm();
            personalInfo.setVisible(false);
            mainView.check();
        });
    }

    private void showAccountInfo() {
        accountInfo.add(personalInfo);
        if (statusDescription1.isVisible() && statusDescription1.getText() != null) {
            UserDto userDto = userClient.getAccount(this);
            name.setValue(userDto.getFirstname());
            lastname.setValue(userDto.getLastname());
            email.setValue(userDto.getEmail());
            contact.setValue(userDto.getPhoneNumber().toString());
            name.setWidth("155px");
            lastname.setWidth("155px");
            contact.setWidth("155px");
            email.setWidth("155px");
        }
    }

    private void removeRegistrationForm() {
        Element registrationForm = mainView.getAccordion().getElement().getChild(0);
        if (statusDescription.getText().equals("")) {
            registrationForm.setVisible(true);
        }
        if (!login.getValue().equals("User")) {
            registrationForm.setVisible(false);
        }
    }

    public Label getStatusDescription() {
        return statusDescription;
    }

    public Label getLoginInfo() {
        return loginInfo;
    }

    public FormLayout getLoginLayout() {
        return loginLayout;
    }

    public VerticalLayout getAccountInfo() {
        return accountInfo;
    }

    public VerticalLayout getStatusLayout() {
        return statusLayout;
    }

    public HorizontalLayout getSignAction() {
        return signAction;
    }

    public VerticalLayout getAdditionalStatusLayout() {
        return additionalStatusLayout;
    }

    public TextField getLogin() {
        return login;
    }

    public TextField getStatusMessage() {
        return statusMessage;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }
}
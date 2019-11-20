package com.individualproject.travel_agency.domain.user.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.UserClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFormLayout {

    private MainView mainView;
    private UserClient userClient = new UserClient();
    private Select<String> select = new Select<>();
    private TextField password = new TextField();
    private TextField newData = new TextField("Create new:");
    private Button update = new Button("Update");
    private Button getDetailsButton = new Button("Get details");
    private Button deleteAccount = new Button("Delete account");
    private HorizontalLayout buttonAction;
    private VerticalLayout updateFormLayout;
    private SignInFormLayout signInFormLayout;
    private Span label = new Span("Type your password");

    public UpdateFormLayout(MainView mainView) {
        this.mainView = mainView;

        getDetailsButton.setWidth("150px");
        label.setVisible(false);
        newData.setWidth("150px");
        select.setWidth("150px");
        select.setItems("LASTNAME", "PHONE NUMBER", "EMAIL");
        update.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        deleteAccount.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        buttonAction = new HorizontalLayout(update, deleteAccount);
        buttonAction.setWidth("80px");
        updateFormLayout = new VerticalLayout(select, newData, label, password, buttonAction);
        password.setVisible(false);
        update.addClickListener(event -> {
            showText();
            userClient.update(this);
            mainView.getAccordion().close();
            clearText();
        });

        deleteAccount.addClickListener(event -> {
            showText();
            label.setVisible(true);
            userClient.deleteAccount(this);
            mainView.getAccordion().close();
            clearText();
        });
    }

    private void showText() {
        label.setVisible(true);
        password.setVisible(true);
        password.setPlaceholder("password");
    }

    private void clearText() {
        label.setVisible(false);
        newData.clear();
        select.clear();
        password.clear();
        password.setVisible(false);
    }

    public Select<String> getSelect() {
        return select;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getNewData() {
        return newData;
    }

    public VerticalLayout getUpdateFormLayout() {
        return updateFormLayout;
    }
}


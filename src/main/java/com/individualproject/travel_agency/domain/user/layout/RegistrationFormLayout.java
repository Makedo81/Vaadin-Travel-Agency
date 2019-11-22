package com.individualproject.travel_agency.domain.user.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.UserClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationFormLayout {

    private TextField name = new TextField("Name:");
    private TextField surname = new TextField("Surnmae:");
    private TextField phone = new TextField("Phone:");
    private TextField email = new TextField("Email:");
    private TextField login = new TextField("Login:");
    private TextField password = new TextField("Password:");
    private Button save = new Button("save");
    private UserClient userClient = new UserClient();
    private VerticalLayout createUserLayout;
    private MainView mainView;

    public RegistrationFormLayout(MainView mainView) {
        this.mainView = mainView;

        name.setHeight("50px");
        name.setWidth("150px");
        surname.setHeight("50px");
        surname.setWidth("150px");
        phone.setHeight("50px");
        phone.setWidth("150px");
        email.setHeight("50px");
        email.setWidth("150px");
        login.setHeight("50px");
        login.setWidth("150px");
        password.setHeight("50px");
        password.setWidth("150px");
        save.setHeight("30px");
        save.setWidth("100px");
        save.addThemeName("primary");
        save.addClickListener(event -> {
            userClient.addUser(this);
            name.clear();
            surname.clear();
            phone.clear();
            email.clear();
            login.clear();
            password.clear();
            mainView.getAccordion().close();
        });
        createUserLayout = new VerticalLayout(
                name,
                surname,
                phone,
                email,
                login,
                password,
                save);
    }
}

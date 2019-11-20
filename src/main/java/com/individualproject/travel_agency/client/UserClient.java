package com.individualproject.travel_agency.client;

import com.individualproject.travel_agency.client.notifications.UpdateFormNotificationProcessor;
import com.individualproject.travel_agency.client.notifications.UserActionNotificationProcessor;
import com.individualproject.travel_agency.domain.user.UserDto;
import com.individualproject.travel_agency.domain.user.layout.RegistrationFormLayout;
import com.individualproject.travel_agency.domain.user.layout.SignInFormLayout;
import com.individualproject.travel_agency.domain.user.layout.UpdateFormLayout;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class UserClient {

    private RestTemplate restTemplate = new RestTemplate();
    private UserActionNotificationProcessor userActionNotificationProcessor = new UserActionNotificationProcessor();
    private UpdateFormNotificationProcessor updateFormNotificationProcessor = new UpdateFormNotificationProcessor();

    public UserDto addUser(final RegistrationFormLayout registrationFormLayout) {

        if (userActionNotificationProcessor.checkInputRegistrationForm(registrationFormLayout)) {
            UserDto userDto = new UserDto();
            userDto.setFirstname(registrationFormLayout.getName().getValue());
            userDto.setLastname(registrationFormLayout.getSurname().getValue());
            userDto.setPhoneNumber(Long.valueOf(registrationFormLayout.getPhone().getValue()));
            userDto.setEmail(registrationFormLayout.getEmail().getValue());
            userDto.setPassword(registrationFormLayout.getPassword().getValue());
            userDto.setLogin(registrationFormLayout.getLogin().getValue());

            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/registerUser")
                    .queryParam("firstname", userDto.getFirstname())
                    .queryParam("lastname", userDto.getLastname())
                    .queryParam("phoneNumber", userDto.getPhoneNumber())
                    .queryParam("email", userDto.getEmail())
                    .queryParam("login", userDto.getLogin())
                    .queryParam("password", userDto.getPassword())
                    .build().encode().toUri();
            System.out.println(url);
            return restTemplate.postForObject(url, userDto, UserDto.class);
        } else System.out.println("Incorrect data format");
        return null;
    }

    public void signUp(final SignInFormLayout signInFormLayout) {
        userActionNotificationProcessor.checkInputSignUpForm(signInFormLayout);
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/signIn")
                .queryParam("login", signInFormLayout.getLogin().getValue())
                .queryParam("password", signInFormLayout.getPasswordField().getValue())
                .build().encode().toUri();
        System.out.println(url);
        restTemplate.put(url, signInFormLayout.getClass());
    }

    public void signOut(final SignInFormLayout signInFormLayout) {
        if (!signInFormLayout.getStatusMessage().getValue().equals("User is logged out")) {
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/signOut")
                    .queryParam("login", signInFormLayout.getStatusDescription().getText())
                    .build().encode().toUri();
            System.out.println(url);
            restTemplate.put(url, signInFormLayout.getClass());
        } else System.out.println("User is already logged out");
    }

    public UserDto getAccount(final SignInFormLayout signInFormLayout) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/get")
                .queryParam("login", signInFormLayout.getStatusDescription().getText())
                .build().encode().toUri();
        System.out.println(url);
        return restTemplate.getForObject(url, UserDto.class);
    }

    public void update(final UpdateFormLayout updateFormLayout) {
        String valueToUpdate = updateFormLayout.getSelect().getValue();
        String newValue = updateFormLayout.getNewData().getValue();
        if (updateFormNotificationProcessor.checkInputUpdateForm(valueToUpdate, newValue)) {
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/update")
                    .queryParam("value1", valueToUpdate)
                    .queryParam("value2", newValue)
                    .queryParam("password", updateFormLayout.getPassword().getValue())
                    .build().encode().toUri();
            System.out.println(url);
            restTemplate.put(url, updateFormLayout.getClass());
        } else System.out.println("Incorrect data format");
    }

    public void deleteAccount(final UpdateFormLayout updateFormLayout) {
        String password = updateFormLayout.getPassword().getValue();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/deleteUser")
                .queryParam("password", password)
                .build().encode().toUri();
        System.out.println(url);
        restTemplate.delete(url);
    }
}



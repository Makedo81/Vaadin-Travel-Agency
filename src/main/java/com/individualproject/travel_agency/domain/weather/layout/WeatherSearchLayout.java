package com.individualproject.travel_agency.domain.weather.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.WeatherForecastClient;
import com.individualproject.travel_agency.domain.weather.WeatherResponseDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;

@Setter
@Getter
public class WeatherSearchLayout {

    public MainView mainView;
    private WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private TextField typeCity = new TextField();
    private final Label message = new Label();
    private Button search = new Button("Check weather");
    private VerticalLayout weatherForecastLayout;

    public WeatherSearchLayout(MainView mainView) {
        this.mainView = mainView;
        typeCity.setPlaceholder("eg.London,uk");
        search.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
        weatherForecastLayout = new VerticalLayout(typeCity, search, message);

        search.addClickListener(event -> {
            try {
                getWeather();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private WeatherResponseDto getWeather() throws IOException {
        WeatherResponseDto weather = weatherForecastClient.checkWeather(this);
        message.setText("Current weather : " + weather.getWeather().get(0).getDescription());
        message.getStyle().set("font-size", "20px");
        message.getStyle().set("Monospaced", "Font.BOLD");
        return weather;
    }
}

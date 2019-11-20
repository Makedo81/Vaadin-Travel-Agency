package com.individualproject.travel_agency.domain.deal.layout;

import com.individualproject.travel_agency.MainView;
import com.individualproject.travel_agency.client.BookingClient;
import com.individualproject.travel_agency.client.CityClient;
import com.individualproject.travel_agency.client.CountryClient;
import com.individualproject.travel_agency.client.DealClient;
import com.individualproject.travel_agency.domain.booking.MealDto;
import com.individualproject.travel_agency.domain.booking.PriceDto;
import com.individualproject.travel_agency.domain.city.CityDto;
import com.individualproject.travel_agency.domain.country.CountryDto;
import com.individualproject.travel_agency.domain.deal.DealDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealLayout {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealLayout.class);
    private MainView mainView;
    private List<DealDto> list;
    private DealDto selectedDeal;
    private PriceDto priceWithMeal;
    private MealDto meal;
    private CityClient cityClient = new CityClient();
    private DealClient dealClient = new DealClient();
    private BookingClient bookingClient = new BookingClient();
    private CountryClient countryClient = new CountryClient();
    private Grid<DealDto> dealsGrid = new Grid<>(DealDto.class);
    private Grid<MealDto> mealPricesGrid = new Grid<>(MealDto.class);
    private ComboBox<String> cityCombo = new ComboBox<>("City");
    private ComboBox<String> countryCombo = new ComboBox<>("Country");
    private DatePicker startDate = new DatePicker("Start");
    private DatePicker endDate = new DatePicker("End");
    private Button searchButton = new Button("Search");
    private Button addButton = new Button("Add deal");
    private VerticalLayout hotelsResultLayout = new VerticalLayout();
    private VerticalLayout searchingForm = new VerticalLayout();
    private VerticalLayout infoLayout = new VerticalLayout();
    private VerticalLayout mealResultLayout = new VerticalLayout();
    private HorizontalLayout actionLayout = new HorizontalLayout();
    private final Label message = new Label();
    private Label notificationMessage = new Label();
    private TextField country = new TextField("Country:");
    private final Label price = new Label();
    private final Label loginStatus = new Label();
    private Button buttonShowBookings = new Button("Show booking");
    private DatePickerCreator datePickerCreator = new DatePickerCreator();
    private StyleSetters styleSetters = new StyleSetters();
    private AddButtonActionProcessor addButtonActionProcessor = new AddButtonActionProcessor();

    public DealLayout(MainView mainView) {
        this.mainView = mainView;

        searchingForm.add(message);
        styleSetters.setFieldsStyle(dealsGrid, mealPricesGrid, searchingForm);
        styleSetters.settingStyles(endDate, startDate, cityCombo, countryCombo, addButton, searchButton, price);
        actionLayout = new HorizontalLayout(searchButton, addButton);
        searchingForm.add(countryCombo, cityCombo, startDate, endDate);

        List<CountryDto> countries = countryClient.getCountry();
        countryCombo.setItems(countries.stream().map(CountryDto::getName)
                .collect(Collectors.toList()));
        countryCombo.addValueChangeListener(event -> {
            List<CityDto> cities = cityClient.getCity(this);
            cityCombo.setItems(cities.stream().map(CityDto::getName)
                    .collect(Collectors.toList()));
        });

        searchButton.addClickListener(event -> {
            getDeals();
            mealResultLayout.getElement().removeAllChildren();
            notificationMessage.setVisible(false);
            searchingForm.remove(price);
        });

        dealsGrid.addItemClickListener(event -> {
            searchingForm.remove(price);
            notificationMessage.setVisible(true);
            selectedDeal = new DealDto();
            selectedDeal.setHotelName(event.getItem().getHotelName());
            selectedDeal.setDateFrom(event.getItem().getDateFrom());
            selectedDeal.setDateTo(event.getItem().getDateTo());
            selectedDeal.setPrice(event.getItem().getPrice());
            LOGGER.info("create  selected deal " + selectedDeal.getHotelName());
            setMealGrid();

            mealPricesGrid.addItemClickListener(e -> {
                showMealPrices();
                String mealType = e.getItem().getMealType();
                int mealPrice = e.getItem().getPrice();
                meal = new MealDto();
                meal.setMealType(mealType);
                meal.setPrice(mealPrice);
            });

            addButtonActionProcessor.setMessageLabelText(selectedDeal, notificationMessage);
            infoLayout.add(notificationMessage);
            styleSetters.setNotificationStyle(this);
        });

        addButtonActionProcessor.addDeals(this, mainView, loginStatus, addButton);
        datePickerCreator.searchDate(this);
    }

    private void getDeals() {
        List<DealDto> result = dealClient.getDeal(this);
        searchingForm.add(dealsGrid, notificationMessage);
        dealsGrid.setItems(result);
    }

    private void showMealPrices() {
        mealPricesGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        mealPricesGrid.addItemClickListener(event1 ->
                calculateDealPriceIncludesMeal());
    }

    public void setMealGrid() {
        List<MealDto> mealDto = dealClient.getMealPrices(this);
        mealResultLayout.add(mealPricesGrid);
        hotelsResultLayout.add(mealResultLayout);
        mealPricesGrid.setItems(mealDto);
    }

    private void calculateDealPriceIncludesMeal() {
        priceWithMeal = dealClient.getDealPrice(this);
        Long priceTotal = priceWithMeal.getTotalPrice();
        price.setWidth("480px");
        searchingForm.add(price);
        price.setText("Total price" + "\n" + " included meal (" + meal.getMealType() + "): " + priceTotal + " EURO");
        mealPricesGrid.addDropListener(e -> mealPricesGrid.deselectAll());
    }

    public Grid<DealDto> getDealsGrid() {
        return dealsGrid;
    }

    public DealDto getSelectedDeal() {
        return selectedDeal;
    }

    public ComboBox<String> getCountryCombo() {
        return countryCombo;
    }

    public VerticalLayout getSearchingForm() {
        return searchingForm;
    }

    public Label getLoginStatus() {
        return loginStatus;
    }

    public ComboBox<String> getCityCombo() {
        return cityCombo;
    }

    public VerticalLayout getHotelsResultLayout() {
        return hotelsResultLayout;
    }

    public VerticalLayout getInfoLayout() {
        return infoLayout;
    }

    public HorizontalLayout getActionLayout() {
        return actionLayout;
    }

    public MealDto getMeal() {
        return meal;
    }

    public VerticalLayout getMealResultLayout() {
        return mealResultLayout;
    }

    public PriceDto getPriceWithMeal() {
        return priceWithMeal;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public Label getMessage() {
        return message;
    }

    public Label getNotificationMessage() {
        return notificationMessage;
    }

    public Label getPrice() {
        return price;
    }
}

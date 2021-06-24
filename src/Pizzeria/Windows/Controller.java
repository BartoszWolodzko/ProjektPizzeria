package Pizzeria.Windows;

import Pizzeria.Logic.*;
import Pizzeria.Readers.ReadIngredients;
import Pizzeria.Readers.ReadSize;
import Pizzeria.Writers.CreateSummary;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox ingredientsContainer;

    @FXML
    private ChoiceBox<String> pizzaSize;

    @FXML
    private Button submitButton;

    @FXML
    private Text summaryField;

    @FXML
    private AnchorPane optionAnchor;

    @FXML
    private TextField street;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField apartmentNumber;

    @FXML
    private Label price;

    @FXML
    private ChoiceBox<String> delivery;

    private final List<CreatePizza> pizzas = new ArrayList<>();
    private final ReadIngredients readIngredients = new ReadIngredients();
    private final ReadSize readSize = new ReadSize();
    private final ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private final ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private final IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
    private final int minNumSelected = 2;
    private boolean isSizeChosen = false;
    private final Order order = new Order();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureCheckBoxes();
        configureDeliveryOptions();
        configureChoiceBox();
        configureDeliveryFields();
        optionAnchor.setDisable(true);
        submitButton.setDisable(true);
    }

    void configureDeliveryOptions() {
        delivery.getItems().add(DeliveryMethods.PICK_UP.getValue());
        delivery.getItems().add(DeliveryMethods.COLLECT.getValue());
        delivery.setOnAction((event) -> optionAnchor.setDisable(!delivery.getValue().contains(DeliveryMethods.PICK_UP.getValue())));
    }

    void configureDeliveryFields() {
        street.textProperty().addListener((observableValue, oldValue, newValue) -> order.setStreetName(street.getText()));

        houseNumber.textProperty().addListener((observableValue, s, t1) -> {
            order.setHomeNumber(houseNumber.getText());
            if (!t1.matches("\\d*")) {
                houseNumber.setText(t1.replaceAll("[^\\d]", ""));
            }
        });

        apartmentNumber.textProperty().addListener((observableValue, s, t1) -> {
            order.setApartmentName(apartmentNumber.getText());
            if (!t1.matches("\\d*")) {
                apartmentNumber.setText(t1.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void activateSubmitButton() {
        if (numCheckBoxesSelected.get() >= minNumSelected) {
            submitButton.setDisable(!isSizeChosen);
        } else {
            submitButton.setDisable(true);
        }
    }

    private void configureChoiceBox() {
        for (int i = 0; i < readSize.getSizes().size(); i++) {
            pizzaSize.getItems().add(readSize.getSizes().get(i) + " cm");
        }
    }

    void configureCheckBoxes() {
        int numberOfCheckboxes = readIngredients.getIngredients().size();

        for (int i = 0; i < numberOfCheckboxes; i++) {
            CheckBox checkBox = new CheckBox(readIngredients.getIngredients().get(i) + " " + readIngredients.getPrice().get(i) + " zł");
            configureCheckBox(checkBox);
            checkBox.setId("checkBox" + i);
            ingredientsContainer.getChildren().add(checkBox);
        }
        pizzaSize.setOnAction((event) -> {
            isSizeChosen = true;
            activateSubmitButton();
        });
        numCheckBoxesSelected.addListener((listener) -> activateSubmitButton());
    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }
        });
    }

    @FXML
    void goToDeliveryMethod() {
        if (pizzas.size() >= 1) {
            if (delivery.getValue().contains(DeliveryMethods.COLLECT.getValue())) {
                new CreateSummary(order.getToString());
                new AlertFinishedOrder();
            } else {
                if (!order.getStreetName().equals("") && !order.getHomeNumber().equals("")) {
                    new CreateSummary(order.getToString());
                    new AlertFinishedOrder();
                }else {
                    new AlertOrderNotComplete();
                }
            }
        }
    }

    void setPriceLabel() {
        double sumPrice = 0;
        for (var element : pizzas
        ) {
            sumPrice += element.getPrice();
        }
        price.setText(sumPrice + " zł");
        order.setPrice(sumPrice);
    }

    @FXML
    void addToSummary() {
        double price_calculated = 0;
        List<String> ingredients = new ArrayList<>();
        for (CheckBox element : selectedCheckBoxes) {
            String[] splittedValue = element.getText().split(" ");
            ingredients.add(splittedValue[0]);
            price_calculated = price_calculated + Double.parseDouble(splittedValue[1]);
        }

        int multiplierOfPrice = Integer.parseInt(pizzaSize.getValue().split(" ")[0])/10; // depends on Pizzerias policy (in here size/10) could be taken from enum next value

        pizzas.add(new CreatePizza(ingredients, price_calculated * multiplierOfPrice, pizzaSize.getValue()));
        StringBuilder textToPrintInSummary = new StringBuilder();
        for (var element:pizzas) {
            textToPrintInSummary.append(element).append(";").append("\n");
        }
        order.setPizzas(pizzas);
        summaryField.setText(String.valueOf(textToPrintInSummary));
        setPriceLabel();
    }
}


package Pizzeria;

import Pizzeria.Logic.CreatePizza;
import Pizzeria.Logic.CreateSummary;
import Pizzeria.Logic.ReadIngredients;
import Pizzeria.Logic.ReadSize;
import javafx.beans.InvalidationListener;
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
    private StringBuilder textToPrintInSummary;
    private final ReadIngredients readIngredients = new ReadIngredients();
    private final ReadSize readSize = new ReadSize();
    private final ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private final ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private final IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
    private final int minNumSelected = 2;
    private boolean isSizeChosen = false;

    //TESTYYYY
    private Order order = new Order();

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
        delivery.getItems().add("Dostawa");
        delivery.getItems().add("Odbiór");
        delivery.setOnAction((event) -> optionAnchor.setDisable(!delivery.getValue().contains("Dostawa")));
    }

    void configureDeliveryFields() {
        houseNumber.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*")) {
                houseNumber.setText(t1.replaceAll("[^\\d]", ""));
            }
        });
        apartmentNumber.textProperty().addListener((observableValue, s, t1) -> {
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
            if (delivery.getValue().contains("Odbiór")) {
                new CreateSummary(order.getToString());
                new AlertFinishedOrder();
            } else {
                if (!street.getText().equals("") && !houseNumber.getText().equals("")) {    //in normal app verification with street names etc. (in another class)
                    new CreateSummary(order.getToString());
                    new AlertFinishedOrder();
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

        pizzas.add(new CreatePizza(ingredients, price_calculated * Integer.parseInt(pizzaSize.getValue().split(" ")[0])/10, pizzaSize.getValue()));
        textToPrintInSummary = new StringBuilder();
        for (var element:pizzas) {
            textToPrintInSummary.append(element).append(";").append("\n");
        }
        order.setPizzas(pizzas);
        summaryField.setText(String.valueOf(textToPrintInSummary));
        setPriceLabel();
    }
}


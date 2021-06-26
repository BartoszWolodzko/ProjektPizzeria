package Pizzeria.Logic;

import javafx.collections.ObservableSet;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Pizza> pizzas = new ArrayList<>();
    private double price = 0;
    private String streetName;
    private String homeNumber;
    private String apartmentName;

    public Order(){}

    public void calculatePizzaPrice(){
        double sumPrice = 0;
        for (var element : pizzas) {
            sumPrice += element.getPrice();
        }
        price = sumPrice;
    }

    public StringBuilder pizzasToPrint(){
        StringBuilder textToPrintInSummary = new StringBuilder();

        for (var element:pizzas) {
            textToPrintInSummary.append(element).append(";").append("\n");
        }
        return textToPrintInSummary;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(ObservableSet<CheckBox> selectedCheckBoxes, String pizzaSize){
        double price_calculated = 0;
        List<String> ingredients = new ArrayList<>();

        for (CheckBox element : selectedCheckBoxes) {
            String[] splittedValue = element.getText().split(" ");
            ingredients.add(splittedValue[0]);
            price_calculated = price_calculated + Double.parseDouble(splittedValue[1]);
        }

        int multiplierOfPrice = Integer.parseInt(pizzaSize.split(" ")[0])/10; // depends on Pizzerias policy (in here size/10) could be taken from enum next value
        pizzas.add(new Pizza(ingredients, price_calculated * multiplierOfPrice, pizzaSize));
    }



    public String getToString() {
        return "Order{" +
                "pizzas=" + pizzas +
                ", price=" + price +
                ", streetName='" + streetName + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                '}';
    }

    public double getPrice() {
        calculatePizzaPrice();
        return price;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
}

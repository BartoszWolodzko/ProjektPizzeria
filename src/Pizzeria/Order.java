package Pizzeria;

import Pizzeria.Logic.CreatePizza;

import java.util.List;

public class Order extends Addres{
    private List<CreatePizza> pizzas;
    private double price;

    public Order(){

    }

    public Order(String streetName, String homeNumber, String apartmentName, List<CreatePizza> pizzas, double price) {
        super(streetName, homeNumber, apartmentName);
        this.pizzas = pizzas;
        this.price=price;
    }

    public List<CreatePizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<CreatePizza> pizzas) {
        this.pizzas = pizzas;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getToString() {
        return "Order{" +
                "pizzas=" + pizzas +
                ", price=" + price +
                super.getToString()+
                "}";
    }
}

package Pizzeria.Logic;

import java.util.List;

public class Order extends Addres {
    private List<CreatePizza> pizzas;
    private double price;

    public Order(){

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
                ", price=" + price +" \n"+
                super.getToString()+
                "}";
    }
}

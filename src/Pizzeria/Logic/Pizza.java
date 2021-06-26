package Pizzeria.Logic;

import java.util.List;

public class Pizza {
    private final List<String> ingredients;
    private final double price;
    private final String size;


    public Pizza(List<String> ingredients, double price, String size) {
        this.ingredients = ingredients;
        this.price = price;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Składniki=" + ingredients +
                ", Cena=" + price +
                ", Rozmiar=" + size;
    }

    public double getPrice() {
        return price;
    }
}

package Pizzeria.Logic;

import java.util.ArrayList;
import java.util.List;

public class CreatePizza {
    private List<String> ingredients = new ArrayList<>();
    private final double price;
    private final String size;

    public CreatePizza(List<String> ingredients, double price, String size) {
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

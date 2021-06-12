package Pizzeria.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadIngredients {
    List<String> ingredients = new ArrayList<>();
    List<String> price = new ArrayList<>();

    public ReadIngredients() {
        try {
            File file = new File("src\\Pizzeria\\Files\\Ingredients.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                ingredients.add(data[0]);
                price.add(data[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getPrice() {
        return price;
    }
}

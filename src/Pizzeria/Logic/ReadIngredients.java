package Pizzeria.Logic;

import Pizzeria.AlertFileErr;

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
            String path = "src\\Pizzeria\\Files\\Ingredients.txt";
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                ingredients.add(data[0]);
                price.add(data[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new AlertFileErr(e.getMessage());
        }
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getPrice() {
        return price;
    }
}

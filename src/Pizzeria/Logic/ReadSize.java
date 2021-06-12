package Pizzeria.Logic;

import Pizzeria.AlertFileErr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadSize {
    List<String> sizes = new ArrayList<>();

    public ReadSize() {
        try {
            File file = new File("src\\Pizzeria\\Files\\Size.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                sizes.add(data[0]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new AlertFileErr(e.getMessage());
        }
    }

    public List<String> getSizes() {
        return sizes;
    }
}


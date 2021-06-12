package Pizzeria.Logic;

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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<String> getSizes() {
        return sizes;
    }
}


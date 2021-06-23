package Pizzeria.Writers;

import Pizzeria.Windows.AlertFileErr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateSummary {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

    public CreateSummary(String text) {
        create(text);
    }

    private void create(String text){
        try{
            LocalDateTime date = LocalDateTime.now();
            String formattedDate = date.format(formatter);
            String path = "src\\Pizzeria\\Files\\Summary"+formattedDate+".txt";
            File file = new File(path);

            file.createNewFile();
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(text);
            fileWriter.close();

        }catch (IOException e){
            new AlertFileErr(e.getMessage());
        }
    }
}

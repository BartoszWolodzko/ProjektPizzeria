package Pizzeria.Logic;

import Pizzeria.AlertFileErr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateSummary {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

    public CreateSummary(String totalPrice) {
        create(totalPrice);
    }

    public CreateSummary(StringBuilder textToPrintInSummary, String totalPrice) {
        create(textToPrintInSummary.toString()+totalPrice);
    }


    public CreateSummary(StringBuilder textToPrintInSummary, String totalPrice, String street, String homeNumber, String apartmentNumber) {
        if (apartmentNumber.equals("")){
            create(textToPrintInSummary.toString()+totalPrice+"\n"+street+" "+homeNumber);
        }else {
            create(textToPrintInSummary.toString()+totalPrice+"\n"+street+" "+homeNumber+"/"+apartmentNumber);
        }
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

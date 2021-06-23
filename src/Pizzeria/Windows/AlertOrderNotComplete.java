package Pizzeria.Windows;

import javafx.scene.control.Alert;

public class AlertOrderNotComplete {
    public AlertOrderNotComplete() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Podaj dane do dostawy!");
        alert.showAndWait();
    }
}

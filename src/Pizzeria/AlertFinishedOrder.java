package Pizzeria;

import javafx.scene.control.Alert;

public class AlertFinishedOrder {
    public AlertFinishedOrder() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Zamówiono!");
        alert.showAndWait();
    }
}

package Pizzeria;

import javafx.scene.control.Alert;

public class AlertFileErr {
    public AlertFileErr(String e) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e);
        alert.showAndWait();
    }


}

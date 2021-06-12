module ProjektPizzeria {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens Pizzeria;
    opens Pizzeria.Logic;
    exports Pizzeria;
}
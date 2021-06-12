module ProjektPizzeria {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens Pizzeria;
    exports Pizzeria.Controllers;
    opens Pizzeria.Controllers;
    opens Pizzeria.Logic;
}
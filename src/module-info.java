module ProjektPizzeria {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens Pizzeria;
    opens Pizzeria.Logic;
    exports Pizzeria;
    exports Pizzeria.Logic;
    exports Pizzeria.Readers;
    opens Pizzeria.Readers;
    exports Pizzeria.Writers;
    opens Pizzeria.Writers;
    exports Pizzeria.Windows;
    opens Pizzeria.Windows;
}
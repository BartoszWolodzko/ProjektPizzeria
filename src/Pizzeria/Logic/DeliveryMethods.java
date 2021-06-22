package Pizzeria.Logic;

public enum DeliveryMethods {
    PICK_UP("Dostawa"),
    COLLECT("Odbiór");

    private final String value;

    DeliveryMethods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

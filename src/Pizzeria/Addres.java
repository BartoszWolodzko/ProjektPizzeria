package Pizzeria;

public class Addres {
    private String streetName;
    private String homeNumber;
    private String apartmentName;

    public Addres(){

    }

    public Addres(String streetName, String homeNumber, String apartmentName) {
        this.streetName = streetName;
        this.homeNumber = homeNumber;
        this.apartmentName = apartmentName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getToString() {
        return  "Addres{" +
                "streetName='" + streetName + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                '}';
    }
}

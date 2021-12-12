package praktikum.data;
import java.util.ArrayList;
import java.util.List;

public class Order {


    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<ScooterColor> color;



    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<ScooterColor> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;

    }

    public Order setColor(List<ScooterColor> color) {
        this.color = color;
        return this;
    }

    public static Order getOrder() {
       final String firstName = "Наталья";
       final String lastName = "Леухина";
       final String address = "город Йошкар-Ола";
       final String metroStation = "Сокольники";
       final String phone = "80000000000";
       final int rentTime = 2;
       final String deliveryDate = "2021-12-20";
       final String comment = "хочу кататься";
       final List<ScooterColor> colorOrder = new ArrayList<>();
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colorOrder);
    }




}






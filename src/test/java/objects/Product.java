package objects;

import java.sql.Timestamp;

public class Product {
    private String number;
    private String name;
    private String quantity;
    private String price;
    private Integer properties;

    public Product(String number, String name, String quantity, String price, Integer properties) {
        this.number = number;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.properties = properties;

        /*this.number = Long.toString(new Timestamp(System.currentTimeMillis()).getTime());
        this.name = "Samus product " + number;
        this.quantity = getRandom(1, 100).toString();
        this.price = String.format("%.2f", getRandom(0.1, 100.0));
        //this.properties = ;*/
    }

    public String getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public Integer getProperties() {
        return this.properties;
    }
}
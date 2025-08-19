package streams;

import java.util.List;

public class Car {
    private int year;
    private Make make;
    private String model;
    private int miles;
    private Color color;
    private Style style;
    private double price;

    public Car(int year, Make make, String model, int miles, Color color, Style style, double price) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.miles = miles;
        this.color = color;
        this.style = style;
        this.price = price;
    }

    public int getYear() {
        return this.year;
    }

    public Make getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getMiles() {
        return this.miles;
    }

    public Color getColor() {
        return this.color;
    }

    public Style getStyle() {
        return this.style;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return this.getYear() + " " + this.getMake().toString() + " " + this.getModel();
    }

    public enum Make {
        Toyota, Honda, Subaru, Lexus
    }

    public enum Color {
        Red, Blue, Black, Silver
    }

    public enum Style {
        SUV, Sedan, Coupe, Wagon, Truck
    }
}

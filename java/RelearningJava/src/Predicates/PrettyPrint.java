package Predicates;

interface CarFormatter {
    String format(Car car);
}

public class PrettyPrint implements CarFormatter {
    public String format(Car car) {
        return car.getYear() + " *** " + car.getMake() + " *** " + car.getModel();
    }

    public static void prettyPrint(Car car, CarFormatter formatter) {
        System.out.println(formatter.format(car));
    }

    public static void main(String[] args) {
        prettyPrint(new Car("GMC", "Yukon", 2018), new PrettyPrint());

        prettyPrint(new Car("Audi", "Quattro", 2018), new CarFormatter() {
            public String format(Car car) {
                return "foo";
            }
        });
    }
}

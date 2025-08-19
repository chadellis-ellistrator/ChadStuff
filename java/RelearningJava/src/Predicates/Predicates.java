package Predicates;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Predicates {
    public List<Car> filterCars(List<Car> cars, Predicate<Car> p) {
        return cars.stream().filter(c -> p.test(c)).sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
    }

    private void printCars(List<Car> cars, String header) {
        System.out.println("==== " + header + " ====");
        for (Car c: cars) {
            System.out.println(c);
        }
    }

    public List<Car> filterCarsMP(List<Car> cars, MyPredicate p) {
        Comparator<Car> comp = (Car c1, Car c2) -> c1.getModel().compareTo(c2.getModel());
        return cars.stream().filter(c -> p.test(c))
            .sorted(comp)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Predicates p = new Predicates();
        List<Car> cars = List.of(
            new Car("Honda", "HR-V", 2017),
            new Car("Honda", "CR-V", 2016),
            new Car("Lexus", "EX 350", 2009)
        );
        p.printCars(cars, "All Cars");
        p.printCars(p.filterCars(cars, (Car c) -> c.getMake().equals("Honda")), "Hondas");
        p.printCars(p.filterCars(cars, (Car c) -> c.getYear() < 2017), "Cars Older than 2017");

        List<Car> newerCars = p.filterCars(cars, new Predicate<Car>() {
            public boolean test(Car car) {
                return car.getYear() > 2016;
            }
        });
        p.printCars(newerCars, "Newer Cars");

        p.printCars(p.filterCarsMP(cars, (Car c) -> c.getYear() < 2017), "foobar");
    }

}

interface MyPredicate {
    boolean test(Car car);
}


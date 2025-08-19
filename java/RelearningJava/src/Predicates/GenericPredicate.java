package Predicates;

import java.util.List;
import java.util.stream.Collectors;

public class GenericPredicate {
    public static<T> List<T> filter(List<T> list, GPredicate<T> gp) {
        return list.stream().filter(l -> gp.test(l)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(
            new Car("Lamborghini", "Countash", 2025),
            new Car("Suzuki", "Spirit", 1994)
        );

        List<Car> fastCars = filter(cars, (Car c) -> c.getMake().equals("Lamborghini"));
        for (Car c: fastCars) {
            System.out.println(c);
        }
    }
}

interface GPredicate<T> {
    boolean test(T t);
}

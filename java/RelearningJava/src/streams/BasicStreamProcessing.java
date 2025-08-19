package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import streams.Car.Color;
import streams.Car.Make;
import streams.Car.Style;

public class BasicStreamProcessing {

    static List<Car> cars = List.of(
        new Car(2014, Make.Honda, "CR-V", 120000, Color.Black, Style.SUV, 13250.0),
        new Car(2013, Make.Subaru, "CrossTrek", 115000, Color.Blue, Style.Wagon, 11750),
        new Car(2009, Make.Lexus, "EX 350", 120000, Color.Silver, Style.SUV, 9000.0)
    );

    public static void main(String[] args) {
        List<String> list = cars.stream()
            .filter(c -> { 
                System.out.println("filtering " + c.getModel());
                return c.getPrice() > 1000;
            })
            .sorted(Comparator.comparing(Car::getYear))
            .map(c -> {
                System.out.println("mapping " + c.getModel());
                return c.toString();
            })
            .limit(3)
            .collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        intList.stream().forEach(i -> System.out.println(i * i));
        List<Integer> newList = intList.stream().map(i -> i*i).collect(Collectors.toList());
        newList.stream().forEach(System.out::println);

        List<Integer> l1 = List.of(1,2,3);
        List<Integer> l2 = List.of(4,5);
        l1.stream().flatMap(i -> l2.stream().map(j -> new int[]{i, j})).forEach(b -> System.out.println(b[0] + ":" + b[1]));

    }
}

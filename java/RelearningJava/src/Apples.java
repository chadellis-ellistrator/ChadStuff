import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Apples {
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }
}

class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return this.weight;
    }

    public Integer getWeightAsInteger() {
        return this.weight;
    }

    public String getColor() {
        return this.color;
    }

    public String toString() {
        return this.getColor() + " apple, " + this.getWeight() + " grams";
    }
}

class Main {
    public static boolean isRedApple(Apple apple) {
        return apple.getColor().equals("red");
    }

    public static void main(String[] args) {
        List<Apple> apples = List.of(
            new Apple(1, "red"),
            new Apple(2, "yellow")
        );

        List<Apple> redApples = Apples.filterApples(apples, Main::isRedApple);
        for (Apple a: redApples) {
            System.out.println(a.getColor());
        }

        List<Apple> heavyApples = Apples.filterApples(apples, (Apple a) -> a.getWeight() > 1);
        for (Apple a: heavyApples) {
            System.out.println(a.getWeight());
        }

        BiFunction<Integer, String, Apple> f = Apple::new;
        Apple redRushApple = f.apply(2112, "red");
        System.out.println(redRushApple);
    }
}

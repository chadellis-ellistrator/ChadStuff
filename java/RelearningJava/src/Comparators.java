import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static java.util.Comparator.comparing;

public class Comparators {
    static Comparator<Apple> c = new Comparator<Apple> () {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeightAsInteger().compareTo(a2.getWeightAsInteger());
        }
    };
    static Comparator<Apple> c2 = (Apple a1, Apple a2) -> a1.getWeightAsInteger().compareTo(a2.getWeightAsInteger());

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
            new Apple(1, "green"),
            new Apple(2, "red")
        );
        list.sort(c);
        list.sort(c2);
        list.sort((Apple a1, Apple a2) -> a1.getWeightAsInteger().compareTo(a2.getWeightAsInteger()));
        // Apple::getWeight is shorthand for (Apple a) -> a.getWeight()
        list.sort(comparing(Apple::getWeight));
    }

}

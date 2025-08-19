import java.util.List;
import java.util.function.Consumer;

public class Consumers {
    public static <T> void apply(List<T> list, Consumer<T> cons) {
        for (T t : list) {
            cons.accept(t);
        }
    }
    public static void main(String[] args) {
        List<Integer> ints = List.of(
            Integer.valueOf(1),
            Integer.valueOf(42)
        );
        apply(ints, (Integer i) -> System.out.println(i));
    }
}

import java.util.function.Function;
import java.util.function.IntFunction;

public class Functions {
    public static <T, R> R doIt(Function<T, R> f, T t) {
        return f.apply(t);
    }

    public static <T, R> R doIt(IntFunction<R> f, int t) {
        return f.apply(t);
    }

    public static void main(String[] args) {
        System.out.println(doIt((Integer i) -> "foo " + i, 4));
        System.out.println(doIt((int i) -> "foo " + i, 5));
    }
}

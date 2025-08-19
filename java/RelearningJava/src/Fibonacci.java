public class Fibonacci {

    public static void upTo(int value) {
        // 0 1 1 2 3 5 8 13
        int fib = 0;
        int lastFib = 1;
        int total = 0;
        if (value < 1) {
            System.out.println("0");
        } else {
            System.out.println("0");
            System.out.println("1");
            while (total < value) {
                total = fib + lastFib;
                if (total < value) {
                    System.out.println(String.valueOf(total));
                    fib = lastFib;
                    lastFib = total;
                }
            }
        }
    }

    public static void main(String[] args) {
        Fibonacci.upTo(1000);
    }
}

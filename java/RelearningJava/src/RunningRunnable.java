public class RunningRunnable {

    public static void run(Runnable r) {
        r.run();
    }
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("r1");
        Runnable r2 = new Runnable() {
            public void run() {
                System.out.println("r2");
            }
        };
        run(r1);
        run(r2);
        run(() -> System.out.println("r3"));
    }
}

@FunctionalInterface
interface Foo {
    int doIt(String s);
    //int doMore();
}

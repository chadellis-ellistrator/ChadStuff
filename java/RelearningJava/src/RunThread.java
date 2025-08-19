import java.time.LocalDateTime;
import java.util.Random;

public class RunThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            public void run() {
                int sleepSeconds = new Random().nextInt(10) + 1;
                try {
                    Thread.sleep(sleepSeconds * 1000);
                } catch (InterruptedException ie) {}
                System.out.println("Slept " + sleepSeconds + " seconds, now time is " + LocalDateTime.now());
            }
        };
        for (int i = 0; i < 50; i++) {
            new Thread(runnable).start();
        }
        //new Thread(() -> System.out.println(LocalDateTime.now())).start();
    }
}

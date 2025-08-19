import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStream {
    long processInParallel(List<Character> data) {
        Instant start = Instant.now();
        data.parallelStream().filter(x -> x == 'b').collect(Collectors.toList());
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    long processInSeries(List<Character> data) {
        Instant start = Instant.now();
        data.stream().filter(x -> x == 'b').collect(Collectors.toList());
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static void main(String[] args) {
        List<Character> list = new ArrayList<>(100000000);
        for (Integer i = 0; i < 100000000; i++) {
            list.add((i % 2 == 0) ? 'a' : 'b');
        }

        ParallelStream p = new ParallelStream();
        long parallelMs = p.processInParallel(list);
        long seriesMs = p.processInSeries(list);
        System.out.println("Parallel took " +  parallelMs + " milliseconds");
        System.out.println("Series took " + seriesMs + " milliseconds");
    }
}

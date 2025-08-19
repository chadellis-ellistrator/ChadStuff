package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TraderTransactions {
    static Trader raoul = new Trader("raoul", "Cambridge");
    static Trader mario = new Trader("mario", "Milan");
    static Trader alan = new Trader("alan", "Cambridge");
    static Trader brian = new Trader("brian", "Cambridge");

    static List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    public static void println(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        // Year 2011 and sorted by value low to high
        List<Transaction> t2011 = transactions.stream()
            .filter(t -> t.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(Collectors.toList());
        System.out.println("== 2011 Transactions sorted by value");
        t2011.stream().forEach(System.out::println);

        // Unique cities in which traders live
        List<String> cities = transactions.stream()
            .map(t -> t.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());
        System.out.println("== Distinct cities");
        cities.stream().forEach(System.out::println);

        // Traders from Cambridge sorted by name
        List<Trader> cTraders = transactions.stream()
            .filter(t -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getTrader)
            .sorted(Comparator.comparing(Trader::getName))
            .distinct()
            .collect(Collectors.toList());
        System.out.println("== Cambridge traders");
        cTraders.stream().forEach(System.out::println);

        // String of all Traders names sorted alphabetically
        Optional<String> names = transactions.stream()
            .map(Transaction::getTrader)
            .sorted(Comparator.comparing(Trader::getName))
            .map(Trader::getName)
            .distinct()
            .reduce((a, b) -> a + " " + b);
        String names2 = transactions.stream()
            .map(t -> t.getTrader().getName())
            .distinct()
            .sorted()
            .collect(Collectors.joining(" "));
        System.out.println("== Sorted names");
        System.out.println(names.get());
        println(names2);

        // Any Traders in Milan?
        boolean inMilan = transactions.stream()
            .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println("++ In Milan");
        System.out.println(inMilan);

        // Transaction values from Cambridge Traders
        List<Integer> tValues = transactions.stream()
            .filter(t -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .collect(Collectors.toList());
        System.out.println("== Cambridge Transaction values");
        tValues.forEach(System.out::println);

        // Highest value of all Transactions
        int value = transactions.stream()
            .map(Transaction::getValue)
            .reduce(0, Integer::max);
        System.out.println("== Highest transaction amount");
        System.out.println(value);

        // Find Transaction with smallest value
        Optional<Transaction> t = transactions.stream()
            .sorted(Comparator.comparing(Transaction::getValue))
            .limit(1)
            .findFirst();
        Optional<Transaction> t2 = transactions.stream()
            .collect(Collectors.minBy(Comparator.comparing(Transaction::getValue)));
        System.out.println("== Smallest value transaction");
        System.out.println(t);
        System.out.println(t2);

        // How many transactions are there?
        long count = transactions.stream().count();
        long count2 = transactions.stream().collect(Collectors.counting());
        System.out.println("== Transaction counts");
        System.out.println(count);
        System.out.println(count2);

        // Sum of all Transaction amounts
        int sum = transactions.stream()
            .collect(Collectors.summingInt(Transaction::getValue));
        int sum2 = transactions.stream()
            .collect(Collectors.reducing(0, Transaction::getValue, (v1, v2) -> v1 + v2));
        int sum3 = transactions.stream()
            .collect(Collectors.reducing(0, Transaction::getValue, Integer::sum));
        int sum4 = transactions.stream().mapToInt(Transaction::getValue).sum();
        int sum5 = transactions.stream().map(Transaction::getValue).reduce(Integer::sum).get();
        println("== Transaction total");
        println(Integer.toString(sum));
        println(Integer.toString(sum2));
        println(Integer.toString(sum3));
        println(Integer.toString(sum4));
        println(Integer.toString(sum5));

        // Average of all Transaction amounts
        double average = transactions.stream().collect(Collectors.averagingInt(Transaction::getValue));
        println("== Average of Transaction amounts");
        println(Double.toString(average));

        // Transaction amount summary
        IntSummaryStatistics stats = transactions.stream().collect(Collectors.summarizingInt(Transaction::getValue));
        println("== Transaction amount summary");
        println(stats.toString());
    }
}

class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return this.getName() + " " + this.getCity();
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return trader.getName() + " " + this.year + " " + this.value;
    }
}

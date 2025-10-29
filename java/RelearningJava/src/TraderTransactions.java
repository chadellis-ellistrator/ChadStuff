import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraderTransactions {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    public List<Transaction> year2011SortedLowToHighValue() {
        return transactions.stream()
            .filter((t) -> t.year() == 2011)
            .sorted((t1, t2) -> t1.value() > t2.value() ? 1 : -1)
            .toList();
    }

    public List<String> uniqueCities() {
        return transactions.stream()
            .map((t1) -> t1.trader().city())
            .distinct()
            .toList();
    }

    public List<Trader> tradersFromCambridge() {
        return transactions.stream()
            .filter((t) -> t.trader().city().equals("Cambridge"))
            .map(t -> t.trader())
            .distinct()
            .sorted((t1, t2) -> t1.name().compareTo(t2.name()))
            .toList();
    }

    public String tradersNames() {
        return transactions.stream()
            .map(t -> t.trader().name())
            .distinct()
            .sorted()
            .toList()
            .toString();
    }

    public static void main(String[] args) {
        TraderTransactions tt = new TraderTransactions();
        System.out.println(tt.year2011SortedLowToHighValue());
        System.out.println(tt.uniqueCities());
        System.out.println(tt.tradersFromCambridge());
        System.out.println(tt.tradersNames());
    }
}

record Trader(String name, String city) {
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

record Transaction(Trader trader, int year, int value) {
    public String toString() {
        return "{" + this.trader + ", " +
            "year: " + this.year + ", " +
            "value:" + this.value + "}";
    }
}
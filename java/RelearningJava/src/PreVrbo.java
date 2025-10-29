import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PreVrbo {

    public static void main(String[] args) {
        //Booking b = new Booking(LocalDate.of(2025, 11, 1), 1000.0f, Collections.emptyList());
        //System.out.println(b.refundDue(LocalDate.of(2025, 10, 1)));

        Policy p1 = new Policy(15, 0.5f);
        Policy p2 = new Policy(25, 1f);
        Booking b2 = new Booking(LocalDate.of(2025, 11, 1), 1000.0f, List.of(p1, p2));
        System.out.println(b2.refundDue(LocalDate.of(2025, 10, 1)));
        System.out.println(b2.refundDue(LocalDate.of(2025, 10, 15)));
        System.out.println(b2.refundDue(LocalDate.of(2025, 10, 20)));
    }
}

record Policy(Integer daysPrior, float percentRefund) {}

record Booking(LocalDate checkInDate, float totalAmount, List<Policy> policies) {
    private float getCancelRate(LocalDate cancelDate) {
        long daysPrior = checkInDate.toEpochDay() - cancelDate.toEpochDay();
        Optional<Policy> p = policies.stream()
            .filter(p1 -> (p1.daysPrior() < daysPrior))
            .sorted((p1, p2) -> p2.daysPrior().compareTo(p1.daysPrior())).findFirst();
        return p.isPresent() ? p.get().percentRefund() : 0f;
    }

    public float refundDue(LocalDate cancelDate) {
        if (cancelDate.compareTo(checkInDate) >= 0) {
            return 0.0f;
        } else {
            return totalAmount * getCancelRate(cancelDate);
        }
    }
}

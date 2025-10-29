import java.time.LocalDate;

/**
 * Monday 00:00 - Sunday 23:59:59
 */
public class WeeklyWorkRecord {
    private LocalDate startingMonday;
    private float hoursWorked;
    private Person person;

    public WeeklyWorkRecord(LocalDate startingMonday, float hoursWorked, Person person) {
        this.startingMonday = startingMonday;
        this.hoursWorked = hoursWorked;
        this.person = person;
    }

    public String getName() {
        return this.person.getFullName();
    }

    public float getRegularPayHours() {
        if (this.hoursWorked > 40.0) {
            return 40.0f;
        } else {
            return this.hoursWorked;
        }
    }

    public float getRegularPay() {
        return this.getRegularPayHours() * this.person.getPayPerHour();
    }

    public float getOvertimePayHours() {
        if (this.hoursWorked > 40.0) {
            return this.hoursWorked - 40.0f;
        } else {
            return 0;
        }
    }

    public float getOvertimePay() {
        return this.getOvertimePayHours() * this.person.getPayPerHour() * Person.OVERTIME_MULTIPLIER;
    }

    public float getTotalPay() {
        return this.getRegularPay() + this.getOvertimePay();
    }
}

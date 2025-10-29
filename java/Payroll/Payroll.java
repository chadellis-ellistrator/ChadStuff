import java.time.LocalDate;

public class Payroll {

    public static void calculate(WeeklyWorkRecord wwr) {
        System.out.println("Employee: " + wwr.getName());
        System.out.println(" Regular Work Hours: " + wwr.getRegularPayHours());
        System.out.println(" Regular Pay: " + wwr.getRegularPay());
        System.out.println(" Overtime Work Hours: " + wwr.getOvertimePayHours());
        System.out.println(" Overtime Pay: " + wwr.getOvertimePay());
        System.out.println(" Total Pay: " + (wwr.getRegularPay() + wwr.getOvertimePay()));
    }

    public static void main(String[] args) {
        Bank b = new Bank("Charles Schwab Bank", 12202211);
        BankAccount ba = new BankAccount(b, 1234567890);
        Person p = new Person("Chad", "Ellis", ba, 60.0f, 0.25f);
        PayAggregator pa = new PayAggregator(p);
        for (int i = 0; i < 12; i ++) {
            WeeklyWorkRecord wwr = new WeeklyWorkRecord(LocalDate.of(2025, 1, 1).plusDays(i*7), 40+i, p);
            calculate(wwr);
            pa.addWorkRecord(wwr);
        }
        pa.printSummary();
    }
}

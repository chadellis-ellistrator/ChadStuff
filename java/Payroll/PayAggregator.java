public class PayAggregator {
    private int payCount;
    private float totalPay;
    private String name;

    public PayAggregator(Person person) {
        this.payCount = 0;
        this.totalPay = 0f;
        this.name = person.getFullName();
    }

    public void addWorkRecord(WeeklyWorkRecord wwr) {
        this.payCount++;
        this.totalPay += wwr.getTotalPay();
    }

    public void printSummary() {
        System.out.println(this.name + ": " + this.totalPay + " for average weekly pay of " + this.totalPay / this.payCount);
    }
}

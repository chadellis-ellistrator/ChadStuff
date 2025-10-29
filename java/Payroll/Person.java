public class Person {
    private String firstName;
    private String lastName;
    private BankAccount bankAccount;
    private float payPerHour;
    private float taxRate;
    public static float OVERTIME_MULTIPLIER = 1.5f;

    public Person(String firstName, String lastName, BankAccount bankAccount, float payPerHour, float taxRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccount = bankAccount;
        this.payPerHour = payPerHour;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public float getPayPerHour() {
        return this.payPerHour;
    }
}
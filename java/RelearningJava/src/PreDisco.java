import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PreDisco {
    private Map<String, Float> hourCount = new HashMap<>();

    public void addHours(String name, float hours) {
        Float empHours = hourCount.get(name);
        if (empHours == null) {
            empHours = 0f;
        }
        hourCount.put(name, empHours + hours);
    }

    public void outputHours() {
        System.out.println(this.hourCount);
    }

    public void printMostHours() {
        if (this.hourCount.isEmpty()) {
            System.out.println("We got no hours here!!!");
        } else {
            Entry<String, Float> high = this.hourCount.entrySet().stream()
                .sorted((h1, h2) -> h2.getValue().compareTo(h1.getValue()))
                .findFirst().get();
            System.out.println("Most hours is " + high.getKey() + " with "
                + high.getValue() + " hours");
        }
    }

    public static void main(String[] args) {
        PreDisco pd = new PreDisco();
        pd.addHours("Chad", 5.5f);
        pd.addHours("Chad", 4.75f);
        pd.addHours("Pierre", 5.5f);
        pd.addHours("Pierre", 6.75f);
        pd.outputHours();
        pd.printMostHours();
    }
}

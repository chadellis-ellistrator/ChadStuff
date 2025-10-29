public class Sept0325 {
    private Mood mood;

    public static void main(String[] args) {
        for (Mood m: Mood.values()) {
            System.out.println("My mood is " + m);
        }
    }
}

enum Mood {
    HAPPY,
    SAD,
    MEH,
    ANGRY,
    SLEEPY,
    BORED,
}
import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {
    private int number;
    private boolean hints;

    private NumberGuesser(boolean hints) {
        this.newGame(hints);
    }

    public void newGame(boolean hints) {
        this.number = new Random().nextInt(100);
        this.hints = hints;
    }

    public boolean guess(int guess) {
        if (guess == this.number) {
            System.out.println("You got it!");
            return true;
        } else if (this.hints) {
            if (guess > this.number) {
                System.out.println("Too high");
            } else {
                System.out.println("Too low");
            }
        }
        return false;
    }

    static class NumberGuesserBuilder {
        boolean hints;

        public NumberGuesserBuilder() {}

        public NumberGuesserBuilder withHints(boolean hints) {
            this.hints = hints;
            return this;
        }

        public NumberGuesser build() {
            return new NumberGuesser(this.hints);        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.console().reader());
        NumberGuesser ng = new NumberGuesserBuilder().withHints(true).build();
        boolean solved = false;
        while (!solved) {
            System.out.println("Provide a guess");
            if (ng.guess(s.nextInt())) {
                System.out.println("Well done!");
                solved = true;
            }
        }
        s.close();
        
    }
}

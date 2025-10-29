public class PreGusto {

    public static int sum(int upto) {
        int sum = 0;
        for (int i = 1; i < upto; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static int largestPrimeFactor(int num) {
        int largest = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                boolean isPrime = true;
                for (int j = 2; j <= i/2 && isPrime; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime && i > largest) {
                    largest = i;
                }
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        System.out.println("Sum of numbers divisible by 3s or 5s up to 1000 is " + sum(1000));
        System.out.println("Largest prime factor of 13195 is " + largestPrimeFactor(13195));
    }
}

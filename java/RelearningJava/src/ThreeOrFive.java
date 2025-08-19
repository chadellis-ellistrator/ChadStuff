public class ThreeOrFive {

    public static int sum(int upto) {
        int sum = 0;
        for (int i=1; i < upto; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    static int sumEvenFibonaccis(int upto) {
        int x = 0;
        int y = 1;
        int sum = 0;
        while (y < upto) {
            int tmp = y;
            y = x + y;
            x = tmp;
            if (y % 2 == 0) {
                sum += y;
            }
        }
        return sum;
    }

    static boolean isPrime(long val) {
        for (long i = 2; i <= val / 2; i++) {
            if (val % i == 0) {
                return false; 
            }
        }
        return true;
    }

    static long largestPrimeFactor(long val) {
        double sqrt = Math.sqrt(val);
        long largest = 1;
        for (long i = 1; i <= sqrt; i++) {
            if (val % i == 0) {
                if (isPrime(i)) {
                    if (i > largest) {
                        largest = i;
                    }
                }
            }
        }
        return largest;
    }

    static boolean isPallindrome(long val) {
        String str = Long.toString(val);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    static long pallindrome(int digits) {
        // largest digits number squared
        // 2 = 99 * 99
        // 3 = 999 * 999
        long sp = 99 * 99;
        for (long i = sp; i > 0; i--) {
            if (isPallindrome(i)) {
                return i;
            }
        }
        return 0;
    }

    static long bruteForcePallindrome() {
        long largest = 0;
        for (int i = 999; i > 100; i--) {
            for (int j = 999; j > 100; j--) {
                if (isPallindrome(i * j)) {
                    if (i * j > largest) {
                        largest = i * j;
                    }
                }
            }
        }
        return largest;
    }

    static long divisible() {
        for (long i = 10; ; i += 10) {
            boolean divisible = true;
            for (int j = 2; j <= 20; j++) {
                if (i % j != 0) {
                    divisible = false;
                    break;
                }
            }
            if (divisible == true) {
                return i;
            }
        }
    }

    static long sumOfSquares(int upto) {
        long result = 0;
        for (int i = 1; i <= upto; i++) {
            result += i*i;
        }
        return result;
    }

    static long sumNaturalsSquared(int upto) {
        long result = 0;
        for (int i = 1; i <= upto; i++) {
            result += i;
        }
        return result * result;
    }

    static long differenceSquares(int upto) {
        return sumNaturalsSquared(upto) - sumOfSquares(upto);
    }

    public static void main(String[] args) {
        System.out.println("Sum of numbers divisible by 3s or 5s up to 1000 is " + sum(1000));

        System.out.println("Sum of even fibonacci numbers up to 4000000 is " + sumEvenFibonaccis(4000000));

        System.out.println("Largest prime factor of 13195 is " + largestPrimeFactor(13195l));
        System.out.println("Largest prime factor of 600851475143 is " + largestPrimeFactor(600851475143l));

        //System.out.println("Largest pallindrome is " + pallindrome(2));
        //System.out.println("Largest pallindrome is " + bruteForcePallindrome());

        System.out.println(divisible());
        System.out.println("Difference squares " + differenceSquares(100));
    }
}
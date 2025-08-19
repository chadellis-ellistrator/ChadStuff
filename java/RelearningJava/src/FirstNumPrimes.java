public class FirstNumPrimes {
  /**
   * Find the first n prime numbers
   * inputs:
   *  num - number of primes to find
   * outputs:
   *  write out each prime number 
   *  return number of primes
   * 
   * isPrime(num)
   *   if num == 0 return false;
   *   loop i from 2 to num / 2
   *      if num % i == 0
   *        return false
   *   return true
   */
  public static boolean isPrime(int num) {
    if (num < 1) {
      return false;
    }
    for (int i = 2; i < num / 2 + 1; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void firstNumPrimes(int numPrimes) {
    int lastPrime = 0;
    for (int i = lastPrime; numPrimes > 0;i++) {
      if (isPrime(i)) {
        System.out.println(i);
        numPrimes--;
        lastPrime = i;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(isPrime(4));
    firstNumPrimes(20);
  }    
}

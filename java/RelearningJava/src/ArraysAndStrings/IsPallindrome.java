package ArraysAndStrings;

import java.util.HashMap;
import java.util.Map;

public class IsPallindrome {

    public static boolean isPallindrome(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c: str.toCharArray()) {
            if (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'z') {
                Integer count = map.getOrDefault(Character.toLowerCase(c), 0);
                map.put(Character.toLowerCase(c), count+1);
            }
        }
        boolean hasOdd = false;
        for (Character c: map.keySet()) {
            if (map.get(c) % 2 != 0) {
                if (hasOdd) {
                    return false;
                }
                hasOdd = true;
            }
        }
        return hasOdd;
    }
    public static void main(String[] args) {
        String str = "Tact Cota";
        System.out.println(isPallindrome(str));
        System.out.println(isPallindrome("aaa"));
        System.out.println(isPallindrome("abacaba"));
    }
}

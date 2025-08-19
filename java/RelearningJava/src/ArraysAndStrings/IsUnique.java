package ArraysAndStrings;

import java.util.ArrayList;
import java.util.List;

public class IsUnique {
    public static boolean isUnique(String str) {
        List<Character> foundChars = new ArrayList<>();
        for (char c: str.toCharArray()) {
            if (foundChars.contains(c)) {
                return false;
            } else {
                foundChars.add(c);
            }
        }
        return true;
    }

    public static boolean isUniqueNative(String str) {
        char[] chars = new char[26];
        for (char c: str.toCharArray()) {
            int pos = c - 'a';
            if (chars[pos] == Character.toLowerCase(c)) {
                return false;
            } else {
                chars[pos] = Character.toLowerCase(c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique("foo"));
        System.out.println(isUnique("bar"));
        System.out.println(isUniqueNative("foo"));
        System.out.println(isUniqueNative("bar"));
    }
}

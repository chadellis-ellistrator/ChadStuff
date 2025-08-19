package ArraysAndStrings;

import java.util.ArrayList;
import java.util.List;

public class CheckPermutation {
    public static boolean isPermutation(String s1, String s2) {
        List<Character> s2Chars = new ArrayList<>();
        for (Character c: s2.toCharArray()) {
            s2Chars.add(c);
        }
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                int j = s2Chars.indexOf(s1.charAt(i));
                if (j == -1) {
                    return false;
                } else {
                    s2Chars.remove(j);
                }
            }
            return s2Chars.size() == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPermutation("foo", "oof"));
        System.out.println(isPermutation("foo", "doof"));
        System.out.println(isPermutation("frodobaggins", "bagginsfrodo"));
    }
}

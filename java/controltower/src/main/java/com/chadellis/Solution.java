package com.chadellis;

import java.io.*;
import java.util.*;

public class Solution {

    public static boolean isValid(String str) {
        if (str.length() < 8 || str.length() > 30) {
            return false;
        }
        char first = str.charAt(0);
        if (!((first >= 'a' && first <='z') || (first >= 'A' && first <= 'Z'))) {
            return false;
        }
        for (int i=1;  i < str.length(); i++) {
            char c = str.charAt(i);
            Character ch = Character.valueOf('c');
            if (!((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9')
                    || c == '_')) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for (int i=0; i < count; i++) {
            String str = sc.nextLine();
            if (isValid(str)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }
    }
}
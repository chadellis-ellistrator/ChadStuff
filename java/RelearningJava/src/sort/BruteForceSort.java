package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteForceSort {

    private static int findLowest(List<Integer> list) {
        int lowest = 999999999;
        int lowestIndex = -1;
        int number = 0;
        for (int i=0; i < list.size(); i++) {
            if ((number = list.get(i)) < lowest) {
                lowest = number;
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    public static List<Integer> sort(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        List<Integer> sortedList = new ArrayList<>();
        while (copy.size() > 0) {
            int lIndex = findLowest(copy);
            sortedList.add(copy.get(lIndex));
            copy.remove(lIndex);
        }
        return sortedList;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3,8,4,1,6,2,5,9,7);
        for (int i: sort(list)) {
            System.out.println(i);
        }
    }
}

package sort;

import java.util.Arrays;
import java.util.List;

public class BubbleSort {
    private void swap(List<Integer> list, int index) {
        Integer tmp = list.get(index);
        list.set(index, list.get(index+1));
        list.set(index+1, tmp);
    }

    private boolean onePassSort(List<Integer> list) {
        boolean swapped = false;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i+1)) {
                swap(list, i);
                swapped = true;
            }
        }
        return swapped;
    }

    public void bubbleSort(List<Integer> list) {
        boolean swapped = true;
        while (swapped) {
            swapped = onePassSort(list);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3,8,4,1,6,2,5,9,7);
        new BubbleSort().bubbleSort(list);
        for (int i: list) {
            System.out.println(i);
        }
    }
}

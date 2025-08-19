package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Quicksort {
    public int mid(int[] ary) {
        return ary.length / 2;
    }

    public static int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays)
                .flatMapToInt(i -> Arrays.stream(i))
                .toArray();
    }    

    public int[] quicksort(int[] ary) {
        int size = ary.length;
        if (size <= 1) {
            return ary;
        }

        int[] newAry = new int[size];
        int mid = mid(ary);
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (ary[i] < ary[mid]) {
                newAry[j++] = ary[i];
            }
        }

        int[] left = new int[j];
        for (int i = 0; i < j; i++) {
            left[i] = newAry[i];
        }

        int[] right = new int[size - j - 1];
        for (int i = 0, k = 0; i < size; i++) {
            if (ary[i] > ary[mid]) {
                right[k++] = ary[i];
            }
        }

        if (left.length > 1) {
            left = quicksort(left);
        }
        if (right.length > 1) {
            right = quicksort(right);
        }

        // Combine left + mid + right
        return Quicksort.merge(left, Arrays.copyOfRange(ary, mid, mid+1), right);
    }

    public static void main(String[] args) {
        int[] ary = {3,8,4,1,6,2,5,9,7};
        Quicksort qs = new Quicksort();
        ary = qs.quicksort(ary);
        for (int i: ary) {
            System.out.println(i);
        }

        int[] ary2 = {1,1,2};
        ary = qs.quicksort(ary2);
        for (int i: ary) {
            System.out.println(i);
        }
    }
}

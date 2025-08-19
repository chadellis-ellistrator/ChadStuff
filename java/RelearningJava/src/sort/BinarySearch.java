package sort;

public class BinarySearch {
    private static final int NOT_FOUND = -1;

    public static int search(int[] ary, int left, int right, int val) {
        if (left == right) {
            if (ary[left] == val) return left;
            else return NOT_FOUND;
        }
        int mid = (right - left)/2 + left;
        if (ary[mid] < val) {
            return search(ary, mid+1, right, val);
        } else if (ary[mid] > val) {
            return search(ary, left, mid-1, val);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] ary = {1,3,5,7,9,11,13,15,17};
        int index = search(ary, 0, ary.length-1, 6);
        System.out.println(index);
    }
}

package ArraysAndStrings;

public class UrlIfy {
    
    /**
     * "Mr John Smith    "
     * "Mr%20John%20Smith"
     * @param ary
     */
    public static void urlIfy(char[] ary) {
        int leftPos = -1;
        for (int i = ary.length - 1; i > 0 && leftPos == -1; i--) {
            if (ary[i] != ' ') {
                leftPos = i;
            }
        }
        int pos = ary.length - 1;
        while (leftPos >= 0) {
            while (leftPos >= 0 && ary[leftPos] != ' ') {
                ary[pos--] = ary[leftPos--];
            }
            while (leftPos >= 0 && ary[leftPos] == ' ') {
                ary[pos--] = '0';
                ary[pos--] = '2';
                ary[pos--] = '%';
                leftPos--;
            }
        }
    }

    public static void main(String[] args) {
        char[] name = " Mr  John Smith        ".toCharArray();
        urlIfy(name);
        System.out.println(name);

    }
}

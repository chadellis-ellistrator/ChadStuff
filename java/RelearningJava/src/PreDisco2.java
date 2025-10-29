public class PreDisco2 {
    public String reverseString(String input) {
        String newString = new String();
        for (int i = input.length() - 1; i >= 0; i--) {
            newString += input.charAt(i);
        }
        return newString;
    }

    public char[] reverseCharArray(char[] input) {
        char[] output = new char[input.length];
        for (int i = input.length - 1, j = 0; i >= 0; i--) {
            output[j++] = input[i];
        }
        return output;
    }

    public static void main(String[] args) {
        PreDisco2 pd = new PreDisco2();
        System.out.println(pd.reverseString("foobar"));
        char[] input = {'a', 'b', 'c'};
        System.out.println(pd.reverseCharArray(input));
    }
}

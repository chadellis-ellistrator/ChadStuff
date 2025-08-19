public class Reverse {
  public static void reverseInPlace(char[] ary) {
    if (ary != null) {
      int pos = ary.length - 1;
      for (int i = 0; i < pos; i++) {
        char tmp = ary[i];
        ary[i] = ary[pos];
        ary[pos] = tmp;
        pos--;
      }
    }
  }

  public static void main(String[] args) {
    char[] foo = new char[] {'f', 'o', 'o', 'd', 'i', 'e'};
    reverseInPlace(foo);
    System.out.println(foo);    
  }    
}

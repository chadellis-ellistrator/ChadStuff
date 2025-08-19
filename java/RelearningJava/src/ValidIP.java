public class ValidIP {

    public static boolean isValid(String str) {
        return str.matches("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b");
    }

    public static boolean isValidBF(String str) {

        int nextDot = 0;
        int lastDot = -1;
        for (int i = 0; i < 4; i++) {
            nextDot = str.indexOf('.', lastDot);
            if (lastDot == -1) {
                lastDot = 0;
            }
            if (nextDot == -1) {
                nextDot = str.length();
            } else if (i == 3) {
                return false; // dot after last number
            }
            try {
                int number = Integer.parseInt(str.substring(lastDot, nextDot));
                if (number > 999) {
                    return false;
                } else if (number == 0) {
                    if (nextDot - lastDot > 1) {
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                return false;
            }
            lastDot = nextDot + 1;   
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("127.0.0.1"));

        //System.out.println(isValidBF("127.0..1"));
        System.out.println(isValidBF("0.0.0.999"));
    }
}

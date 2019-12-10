package replit9_3;

import java.util.StringTokenizer;

class Main {
    public static int string_sum(String str) {
        // fill here!
        int sum = 0;
        int i = 0;
        StringTokenizer token = new StringTokenizer(str, " ");
        int counttoken = token.countTokens();
        while (i < counttoken) {
            try {
                sum += Integer.parseInt(token.nextToken());
                i++;
            }
            catch(NumberFormatException e) {
                return 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(string_sum("100 q 300")); // 0
        System.out.println(string_sum("100 300")); // 400
        System.out.println(string_sum("300 500 900 300 900 1000"));
        System.out.println(string_sum("300 100 d 500"));
    }
}

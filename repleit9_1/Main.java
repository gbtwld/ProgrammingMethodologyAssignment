package repleit9_1;

import java.lang.*;
class Main {

    public static String len_check(String str) {
        // fill here!

            if (str == null) {
                throw new RuntimeException("null");
            }
            else if (str.length() > 20) {
                throw new RuntimeException("More than 20 words");
            }
            return str;

    }
    public static void main(String[] args) {


        System.out.println(len_check(null));
    }
}
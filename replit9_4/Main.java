package replit9_4;

class Main {
    public static int fact(int n) {

        try {
            // fill here!
            if (n <= 1) {
                return n;
            }
            else {
                return fact(n-1) * n;
            }
        }
        catch (IllegalArgumentException e) {
            // fill here!
            System.out.println("Negative numbers cannot be calculated");
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.println(fact(5));
        //120

        System.out.println(fact(-5));
        //Negative numbers cannot be calculated
        //-5


    }
}

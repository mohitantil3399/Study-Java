import java.util.Scanner;

public class exponential {
    public static long valueExponential(long x, long n) {
        long result = 1;
        long count = 0;
        if (n == 0){
             return 1;// Anything to the power of 0 is 1
            } 

        do {
            result *= x;
            count++;
        } while (count < n);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the base : ");
        long x = sc.nextLong();
        System.out.print("Enter the power of base:  :");
        long n = sc.nextLong();
        System.out.println(x + "^" + n + " = " + valueExponential(x, n));
        sc.close();
    }
}

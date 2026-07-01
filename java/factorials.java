import java.util.*;
import java.math.BigInteger;  // 💥 Supports massive numbers

public class factorials {
    public static void factorial(int n) {
        // Handle negative input
        if (n < 0) {
            System.out.println("Invalid input.");
            return;
        }

        // Use BigInteger for large factorials
        BigInteger factorial = BigInteger.ONE;
        for (int i = n; i >= 1; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        System.out.println("Factorial of " + n + " is: " + factorial);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        factorial(n);
        sc.close();
    }
}


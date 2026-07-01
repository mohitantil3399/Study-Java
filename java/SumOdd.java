// ...existing code...
import java.util.Scanner;

public class SumOdd {

    public static void printSumOdd(int n) {
        if (n <= 0) {
            System.out.println("Please enter a number greater than 0.");
            return;
        }
        long sumOdd = 0L;
        System.out.println("The sum of odd numbers between 1 and " + n + " is:");
        for (int i = 1; i <= n; i += 2) {
            sumOdd += i;
        }
        System.out.println(sumOdd);
    }

    public static long sumEven(int m) {
        if (m <= 0) {
            System.out.println("Please enter a number greater than 0.");
            return 0L;
        }
        long sum = 0L;
        // sum even numbers from 2..m (exclude 0)
        for (int k = 2; k <= m; k += 2) {
            sum += k;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the last number till which you want to add odd numbers (starting from 1): ");
            int n = sc.nextInt();
            printSumOdd(n);

            System.out.print("Enter a number up to which you want the sum of even numbers: ");
            int m = sc.nextInt();
            System.out.println("Sum of even numbers up to " + m + " is: " + sumEven(m));
        } finally {
            sc.close();
        }
    }
}
// ...existing code...
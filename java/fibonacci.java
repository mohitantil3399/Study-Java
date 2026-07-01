import java.util.Scanner;
class fibonacci {

    public static void printFibonacciSeries(int n){
        // Initialize first two terms
        int first = 0, second = 1;

        System.out.println("Fibonacci Series:");
        for (int i = 1; i <= n; i++) {
            System.out.print(first + " ,");

            // Calculate next term
            int next = first + second;
            first = second;
            second = next;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number:");
        int n = sc.nextInt();
        printFibonacciSeries(n);
        sc.close();
    }
}
import java.util.Scanner;

public class factortree {

    public static void printFactorTree(int num, String indent) {
        int factor = smallestFactor(num);
        if (factor == num) {
            System.out.println(indent + num); // Prime number
        } else {
            System.out.println(indent + num);
            printFactorTree(factor, indent + "  ");
            printFactorTree(num / factor, indent + "  ");
        }
    }

    public static int smallestFactor(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0)
                return i;
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int num = scanner.nextInt();

        if (num <= 1) {
            System.out.println("Please enter a number greater than 1.");
        } else {
            System.out.println("Factor Tree:");
            printFactorTree(num, "");
        }
        scanner.close();
    }
}


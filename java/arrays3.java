import java.util.Scanner;

public class arrays3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the array size: ");
        int size = sc.nextInt();
        int[] numbers = new int[size];

        System.out.println("Enter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
        }

        StringBuilder positives = new StringBuilder();
        StringBuilder negatives = new StringBuilder();
        int zeroCount = 0;

        for (int num : numbers) {
            if (num > 0) {
                if (positives.length() > 0) {
                    positives.append(", ");
                }
                positives.append(num);
            } else if (num < 0) {
                if (negatives.length() > 0) {
                    negatives.append(", ");
                }
                negatives.append(num);
            } else {
                zeroCount++;
            }
        }

        // Print results
        if (positives.length() > 0) {
            System.out.println("Positive numbers are: " + positives);
        } else {
            System.out.println("No positive numbers entered");
        }

        if (negatives.length() > 0) {
            System.out.println("Negative numbers are: " + negatives);
        } else {
            System.out.println("No negative numbers entered");
        }

        System.out.println("Number of zeros entered: " + zeroCount);

        sc.close();
    }
}


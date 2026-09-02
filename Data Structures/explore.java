import java.util.Scanner;

public class explore {

    public static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.println(s + "   ");
        }
        System.out.println();
    }

    public static void insertionSort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            String current = arr[i];
            int j = i - 1;

            // Compare strings lexicographically
            while (j >= 0 && arr[j].compareTo(current) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of Array: ");
        int r = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] arr = new String[r];

        for (int i = 0; i < r; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            arr[i] = sc.nextLine();
        }

        insertionSort(arr);

        System.out.println("\nSorted Array (using Insertion Sort):");
        printArray(arr);

        sc.close();
    }
}

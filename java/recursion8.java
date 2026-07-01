import java.util.Scanner;

public class recursion8 {
    public static boolean[] charMap = new boolean[26];

    public static void removeDuplicates(String myr, int idx, String newString) {
        if (idx == myr.length()) {
            System.out.println("Result without duplicates: " + newString);
            return;
        }

        char currChar = myr.charAt(idx);
        if (!charMap[currChar - 'a']) {
            newString += currChar;
            charMap[currChar - 'a'] = true;
        }

        removeDuplicates(myr, idx + 1, newString);
    }

    public static void main(String[] args) {
        Scanner js = new Scanner(System.in);
        System.out.print("Enter your String: ");
        String myr = js.nextLine().toLowerCase(); // Convert to lowercase for simplicity

        removeDuplicates(myr, 0, "");
        js.close();
    }
}

import java.util.*;

public class SimpleKeypadInput{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder message = new StringBuilder();
        Map<Integer, String> keypad = new HashMap<>();

         System.out.println("Keypad Mapping:");
        System.out.println("2 -> A B C\n3 -> D E F\n4 -> G H I\n5 -> J K L");
        System.out.println("6 -> M N O\n7 -> P Q R S\n8 -> T U V\n9 -> W X Y Z");
       



        // Key mappings(input of keypad as integer , value as string)
        keypad.put(2, "ABC");
        keypad.put(3, "DEF");
        keypad.put(4, "GHI");
        keypad.put(5, "JKL");
        keypad.put(6, "MNO");
        keypad.put(7, "PQRS");
        keypad.put(8, "TUV");
        keypad.put(9, "WXYZ");

        System.out.println("Enter keypad input using space between letters and double space between words:");
        String input = sc.nextLine().trim();//this trim the starting space and ending space of the input 
         //  string as input based on the keypad enteries:
        String[] tokens = input.split(" ");
        //Purpose: This line splits the trimmed string into individual words, using the space character " " as the separator.

        int i = 0;

        do {
            if (tokens[i].isEmpty()) {
                // Detected double space , double space means nxt word(token)begins 
                message.append(" ");
                i++; // Skip to next token , token is the split input in words of a sentence.
            } else {
                int digit = Character.getNumericValue(tokens[i].charAt(0));
                int count = tokens[i].length(); // Repetition count
                String letters = keypad.getOrDefault(digit, "");

                // Safe check
                if (count <= letters.length()) {
                    message.append(letters.charAt(count - 1));
                } else {
                    message.append('?'); // Unknown mapping
                }
                i++;
            }
        } while (i < tokens.length);
        //message is the final string output

        System.out.println("Output: " + message.toString());
        sc.close();
    }
}

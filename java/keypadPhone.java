import java.util.*;

public class keypadPhone {
    // Mapping of digits to letters as per traditional mobile keypads
    static Map<Character, String> keypadMap = new HashMap<>();

    static {
        keypadMap.put('2', "ABC");
        keypadMap.put('3', "DEF");
        keypadMap.put('4', "GHI");
        keypadMap.put('5', "JKL");
        keypadMap.put('6', "MNO");
        keypadMap.put('7', "PQRS");
        keypadMap.put('8', "TUV");
        keypadMap.put('9', "WXYZ");
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Character> keys = keypadMap.keySet();
        for (Character key : keys) {
        System.out.println(key + " ->" + keypadMap.get(key));
    }
      System.out.println();
        System.out.print("Enter sequence (e.g. 222#3##4): ");
        String input = sc.nextLine();

        StringBuilder output = new StringBuilder();
        char prev = ' ';
        int count = 0;

        for (char c : input.toCharArray()) {
            if (c == '#') {
                if (keypadMap.containsKey(prev)) {
                    String letters = keypadMap.get(prev);
                    output.append(letters.charAt((count - 1) % letters.length()));
                }
                count = 0;
                prev = ' ';
            } else {
                if (c == prev) {
                    count++;
                } else {
                    if (keypadMap.containsKey(prev)) {
                        String letters = keypadMap.get(prev);
                        output.append(letters.charAt((count - 1) % letters.length()));
                    }
                    prev = c;
                    count = 1;
                }
            }
        }

        // Append final character if input doesn't end with '#'
        if (keypadMap.containsKey(prev)) {
            String letters = keypadMap.get(prev);
            output.append(letters.charAt((count - 1) % letters.length()));
        }

        System.out.println("Output: " + output);
        sc.close();
    }
}

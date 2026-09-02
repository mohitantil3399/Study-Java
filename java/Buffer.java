import java.util.Scanner;
public class Buffer {
    public static void main(String[] args){
        // All input statements are read from the System.in input stream buffer
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number one: ");
        int num1 = sc.nextInt();
        System.out.print("Enter number 2: ");
        int num2 = sc.nextInt();

        // Important: nextInt() does not consume the trailing newline '\n'.
        // To read a String afterwards with nextLine(), consume the leftover newline first:
        sc.nextLine(); 

        System.out.print("Enter the string: ");
        String str = sc.nextLine();
        System.out.print("Enter number 3: ");
        int num3 = sc.nextInt();
        System.out.println("Result:\n" + num1 + "\n" + num2 + "\n" + str + "\n" + num3);
        sc.close();

        // StringBuilder sb = new StringBuilder();
        // for (char i = 0;i<1001;i++){
        //     sb.append(i).append(", ");
        // }
        //     System.out.println(sb);
    }
}

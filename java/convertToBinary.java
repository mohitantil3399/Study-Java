import java.util.Scanner;
public class convertToBinary {
    public static void decimalToBinary(int x){
        StringBuilder binary = new StringBuilder();
        if ( x == 0 ){
        System.out.println("The binary representation is : 0 ");
        return;
        }
     while (x>0){//remainder precending 
        int remainder = x%2;
       // as the filling of stringbuilder starts from bottom to upward that means:
       // automatically using string builder the binary numbers can be arranged from right to left or bottom to upward squence . 
        binary.insert(0, remainder);
        x = x/2;
     } System.out.println("The binary representation is: " + binary);
     return;
}
   

    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     System.out.print("Enter a number to convert in binary: "); 
     int x = sc.nextInt();
     decimalToBinary(x);
     System.out.print("Enter the binary number you want to convert to decimal: ");
     String r = sc.next();
     int sum = binaryToDecimal(r);
     System.out.println(sum);
     sc.close();
    }

    public static int binaryToDecimal(String r){
        int j = 1;
        int sum = 0;
        for (int i = r.length() - 1; i >= 0; i--) {
            char k = r.charAt(i);
            int bit = k - '0'; // Convert char '0' or '1' to int 0 or 1
            sum += bit * j;
            j *= 2;
        }
        return sum;
    }
}
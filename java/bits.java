import java.util.Scanner;
public class bits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a  number : ");
        int n = sc.nextInt();
        System.out.print("Enter the position at which the operation to be performed: ");
        int pos = sc.nextInt();
        System.out.print("Enter either 1 for set operation or 0 for clear operation: ");
        int op = sc.nextInt();
        int BitMask = 1<<pos;
       if( op == 1){
        int num = n | BitMask;
        System.out.println(num);
       }else { 
        int newBit = ~(BitMask);
        int num = n&newBit;
        System.out.println(num);
       }
    }
}

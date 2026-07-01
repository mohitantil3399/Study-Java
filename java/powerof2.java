// import java.util.Scanner;

// public class powerof2 {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter a number to check: ");
//         int x = sc.nextInt();

//         boolean isPower = true;

//         if (x < 1) {
//             isPower = false;
//         } else {
//             while (x > 1) {
//                 if (x % 2 != 0) {
//                     isPower = false;
//                     break;
//                 }
//                 x = x / 2;
//             }
//         }

//         if (isPower) {
//             System.out.println("Yes, it's a power of 2.");
//         } else {
//             System.out.println("No, it's not a power of 2.");
//         }
//     }
// }

// to claculate power of 2 till n ;using recursion 
import java.util.Scanner;
public class powerof2{
    public static void printPower(int n,int i,int exp){
        if(i==n ){
            exp *= 2;
            System.out.println("the value of "+"2^ "+n+" = "+ exp);
            return;
        }exp *= 2;
        printPower(n, i+1, exp);
    }
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        System.out.print("Enter a number : ");
        int n = ms.nextInt();
        printPower(n, 1, 1);
        
        Scanner sc = new Scanner(System.in);
         System.out.print("Enter a number to check if is an eponent of 2 : ");
        int x = sc.nextInt();

        boolean isPower = true;

        if (x < 1) {
            isPower = false;
        } else {
            while (x > 1) {
                if (x % 2 != 0) {
                    isPower = false;
                    break;              
                 }
                x = x / 2;
         }     
     }

         if (isPower) {
             System.out.println("Yes, it's an exponent of 2.");
         } else {
             System.out.println("No, it's not an exponent of 2.");
         }
         ms.close();
         sc.close();
    }
}
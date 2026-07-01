import java.util.*;
public class functions1 {
    public static int calculateMultiply(int a,int b){
        int multiply = a*b;
        return multiply;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("enter a number:");
        int  a= sc.nextInt();
         System.out.print("enter other number:");
        
        int b = sc.nextInt();
        System.out.println("The product of two numbers is:");
        int multiply = calculateMultiply(a, b);
        System.out.println(multiply);
        sc.close();
    }
}


import java.util.Scanner;
public class function2{
    public static void factorial(int n){
        //if number is less than 0
        if(n<0){
            System.out.println("Invalid input.");
            return;

        } int factorial = 1;
          for(int i=n;i>=1;i--){
            factorial=factorial*i;
          }
            System.out.println(factorial);
            return;
          
    }
    public static void main( String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number:");
        int n = sc.nextInt();
        factorial(n);
        sc.close();
    }
}



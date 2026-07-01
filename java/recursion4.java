import java.util.Scanner;
public class recursion4 {
    public static void printFib(long a , long b , long n ){
        if ( n == 0 ){
            return;
        }
        long c = a+b;
       
        System.out.println(c+" ");
         printFib(b,c,n-1);
        }
        public static void main(String[] args) {
            System.out.print("Enter a number: ");
            Scanner ms = new Scanner(System.in); 
            long n = ms.nextInt();
            long a = 0; long b = 1;
            System.out.println(a+" ");
            System.out.println(b+" ");
          printFib(a,b,n-2);
          
        }
}

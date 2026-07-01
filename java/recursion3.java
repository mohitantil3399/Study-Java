import java.util.Scanner;
public class recursion3{
    public static int printFactorial(int n ){
     if(n==1 || n==0){
        return 1;
     }
       int factorialof_nMinus1 = printFactorial(n-1);
       int factorialof_n = n*factorialof_nMinus1;
       return factorialof_n;
    }
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = ms.nextInt();
        int ans = printFactorial(n);
        System.out.println(ans);
        ms.close();
    }
}
    


import java.util.Scanner;

public class recursion1 {
    public static void printNUmbers(int n ){
        if ( n ==0){
            return;
        }System.out.println(n);
        printNUmbers(n-1);
    }
    public static void main(String[] args) {
        Scanner sd = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sd.nextInt();
        printNUmbers(n);
        sd.close();
    }
}

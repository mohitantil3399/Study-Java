import java.util.*;
public class checkgreater {
    public static void printGreaterNumber(long n ,long m){
        if(n>m){
            System.out.println("The number "+n+" is greater than the number"+m);
            return;
        }else{
            System.out.println("The number "+m+" is greater than the number "+n);
            return;
        }
    }
public static void main(String[] args) {
    Scanner sc = new Scanner (System.in);
    System.out.print("Enter number n : ");
    long n = sc.nextInt();
    System.out.print("Enter number m:");
    long m = sc.nextInt();
    printGreaterNumber(n,m);
    sc.close();
}
}

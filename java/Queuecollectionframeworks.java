import java.util.Queue;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayDeque;
public class Queuecollectionframeworks {
    public static void main(String[] args) {
        Queue <Integer> q = new ArrayDeque<>();
        int i = 1;
        while(i<=10){
            q.add(i);
            i++;
        }Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number whose table you want to print : ");
        int n = sc.nextInt();
        System.out.println();
        System.out.println("The table of "+n+" is : ");
        while(!q.isEmpty()){
            System.out.println(n*q.peek());
            q.remove();
        }Queue <Integer> q1 = new ArrayDeque<>();
        System.out.print("Enter a number whose factorial you want to print : ");
        int m = sc.nextInt();
       for (int j = 1; j <=m;j++){
        q1.add(j);
       }
        BigInteger factorial = BigInteger.ONE;
         while(!q1.isEmpty()){
            factorial = factorial.multiply(BigInteger.valueOf(q1.peek()));
            q1.remove();
         }
         System.out.println("The factorial of "+m + " is :"+factorial);
    }
}

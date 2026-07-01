import java.util.*;
public class evenfunction {
   public static void checkIfEven(int a){
    if(a%2==0){
        System.out.println("The number "+a+" is an even number.");
     } else{
            System.out.println("The numer "+a+" is not a even number.");
        }int table=1;
        System.out.println("Table of "+a+" is as follows:");
         for (int i=1;i<=10;i++){
           table = a*i;
           System.out.println(table);
         }return;
    
   } public static void main( String[]args){
    Scanner sc = new Scanner( System.in);
    System.out.print("Enetr a number to check:");
  int a = sc.nextInt();
     checkIfEven(a);

     sc.close();
}
}

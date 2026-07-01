import java.util.Scanner;

public class prime {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter a number: ");
        int d = sc.nextInt();
        boolean isPrime = false;
        if(d<=1){
            System.out.println("It is not a prime number");
    }else{
        for(int i =2;i<=Math.sqrt(d);i++){
         if(d%i==0){
         isPrime = true;
         break;  
         }
         } if (isPrime){
             System.out.println(d+" is not a prime number");
         } else {
            System.out.println(d + " is a prime number");
            
        }
     } 
     sc.close();
    }
 }           





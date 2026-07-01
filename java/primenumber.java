import java.util.*;
public class primenumber{

public static void checkPrime(int n){
//if n == 1 or 0 
if(n==1||n==0){
    System.out.println("The input number "+n+" is not a prime number");
    
} Boolean isPrime = true;
for(int i= 2; i<=Math.sqrt(n);i++){
if(n % i ==0){
     isPrime = false;
    break;
} 
}if(isPrime) {
    System.out.print("The given number "+n+" is a prime number.");
}else{
    
    System.out.println("The given number "+n+" is not a prime number.");
}

return ;

}

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check if its prime or not:");
       int n = sc.nextInt();
       checkPrime(n);

    }
}
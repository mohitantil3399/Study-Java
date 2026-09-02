import java.util.Scanner;
public class recursion2 {
    public static void printSum(int i , int n, int sum ){
    if ( i == n ){//when i becomes equal to n then ;
    sum += i ;//add it to sum ;
    System.out.print(sum);//final sum ;
    return;
}sum += i ; 
printSum( i+1,n, sum);//i increses from 1 to n 
        
    }
    public static void main(String[] args) {
        Scanner sd = new Scanner(System .in );
        System.out.print("Enter a number : ");
        int n = sd.nextInt();
        printSum(1,n,0);//define the initial values
        sd.close();
    }
}

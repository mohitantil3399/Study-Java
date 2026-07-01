import java.util.Scanner;
public class recursion15 {
    public static long callGuests( long n){
        if ( n <=1){
            return 1;
        }// individually calling the guests 
        long way1 = callGuests(n-1);
        //calling in pairs 
        long way2 = (n-1)* callGuests(n-2);//there left n-1 ways to choose partner and n-2 guests to call by the same method
       return way1 + way2;
    } 
    public static void main(String[] args) {
        Scanner js = new Scanner(System.in);
        System.out.print("Enter the number of guests to be called : ");
        long n = js.nextInt();
        System.out.println("The total numbers of ways are: "+callGuests(n));
        js.close();
    }
}

import java.util.*;
public class sumation{
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter a number to print the table of it: ");
        int n = sc.nextInt();
        //int sum = 0;
       // for (int j=1;j<=n;j++){
       // sum = sum + j;
       // }
       // System.out.println(sum);
       for (int j=1;j<=10;j++){
       System.out.println(n+"*"+j+" = "+n*j);

       }
       sc.close();
    
    }
}
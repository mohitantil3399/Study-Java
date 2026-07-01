import java.util.*;
public class nestedloop13 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of rows(less than 10):");
        int n= sc.nextInt();
        for(int i=1;i<=n;i++){
          for( int j =n-i;j>=1 ;j--){
            System.out.print(" ");
          }for(int j =1;j<=i;j++){
            System.out.print(i+" ");
          }System.out.println();
        }
    }
}

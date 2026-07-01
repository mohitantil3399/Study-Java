import java.util.*;
public class nestedloop8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//enter number of rows to print.
        System.out.print("Enter number of rows:");
        int a = sc.nextInt();
        for(int i = 1;i<=a;i++){
            for(int j = 1; j<=i;j++){
             if((i+j)%2==0){//checking even ,by asumming lower traingular matrix i&j sum,if even it's 1
                System.out.print("1"+" ");
             }else{    //if sum is not even its 0
                System.out.print("0"+" ");
             }
            }System.out.println();
        }
    }
}

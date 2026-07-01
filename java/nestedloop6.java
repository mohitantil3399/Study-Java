import java.util.*;
public class nestedloop6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number:");
        int v =sc.nextInt();
        for(int i = v ;i>=1;i--){
            for(int j=1;j<=i;j++){
               System.out.print(j+" ");
            }System.out.println();
        }

    }
}

import java.util.*;
public class nestedloop7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows:");
        int x = sc.nextInt();
        int y = 1;//starting number of printing in the row 1.
        for(int i = 1;i<=x;i++){//i starts from row 1 to row x
            for(int j = 1;j<=i;j++){//number of elements to come in a row 
             System.out.print(y+" ");y++;//the elements to come in the row
                }System.out.println();
        }
    }
}

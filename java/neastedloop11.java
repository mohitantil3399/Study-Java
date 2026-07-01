import java.util.*;
public class neastedloop11 {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     System.out.print("Enter number of rows to form triangle:");
        int p = sc.nextInt();//intoducing rows
        for(int i=1;i<=p;i++){  //intoducing preceding space
            for (int j = 1; j<=p-i;j++){
                System.out.print("  ");
                //intoducing input numbers as decending order
              }  for(int k=i;k>=1;k--){
             System.out.print(k+" ");
             //introducing input numbers in ascending order
            }for (int s= 2;s<=i;s++){
                System.out.print(s+" ");
            }for(int l = p-i;l>=1;l--){//adding decending space
               System.out.print(" ");
            }System.out.println();
        
    }
}
}
import java.util.*;
public class nestedloop16 {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.print("enter number of rows:");
        int n =sc.nextInt();
        for(int i = 1;i<=n;i++){
          for(int j =1;j<=i;j++){
             if(j==i||j==1){
              System.out.print("X"+" ");
            }else{
              System.out.print("  ");
            }
              
            }for(int k = (2*(n-i));k>=1;k--){
              System.out.print("  ");
            }for( int j = 1;j<=i;j++){
              if(j==i||j==1){
              System.out.print("X"+" ");
            }else{
              System.out.print("  ");
            } 
            }
          System.out.println();
        }
        for(int i = n;i>=1;i--){
          for(int j =1;j<=i;j++){
             if(j==i||j==1){
              System.out.print("X"+" ");
            }else{
              System.out.print("  ");
            }
              
            }for(int k = (2*(n-i));k>=1;k--){
              System.out.print("  ");
            }for( int j = 1;j<=i;j++){
              if(j==i||j==1){
              System.out.print("X"+" ");
            }else{
              System.out.print("  ");
            }
              
            
            }
          System.out.println();
        }
    }
  }
  
        
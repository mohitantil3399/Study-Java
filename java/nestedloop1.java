import java.util.*;
public class nestedloop1{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a  value:");
        String p = sc.next();
        //outerloop
        for(int i = 0;i<=87;i++){
          //inner loop 
           for(int j = 0;j<=20;j++){
            System.out.print(p+" ");
           }
           //statement of outer loop:
           System.out.println(p);
        }
        sc.close();
    }
}
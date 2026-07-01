
import java.util.Scanner;

public class arrays1 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of the String : ");
        int size = sc.nextInt();
        System.out.print("Enter the name and press enter to enter the next name : ");
        String names[]= new String[size];
        //input
        for(int i = 0;i<size;i++){
            names[i]=sc.next();
        }
        //output
        for(int i = 0;i < names.length ; i++){
            System.out.println("Name "+(i+1)+" is: "+names[i]);
        }
      sc.close();
    }
    
}

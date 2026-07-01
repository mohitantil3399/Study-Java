import java.util.Scanner;
public class arrays2 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the sze of array : ");
        int size = sc.nextInt();
        int numbers[] = new int[size];
        System.out.println("Start entering the numbers onr by one : ");
        //input loop for entering numbers 
     for ( int i = 0;i<numbers.length;i++){
         numbers[i] = sc.nextInt(); 
     }
     //output loop for sorting and printing them 
        for (int number : numbers) {
            if (number < 0) {
                System.out.println("negative numbers are :" + number);

            } else if (number > 0) {
                System.out.println("positive numbers are :" + number);
            } else {
                System.out.println("other numbers are:"+number);
            }
        }
        sc.close();
     
    }     
}

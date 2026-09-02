
import java.util.Scanner;
public class checkArrayAscending {
    public static boolean checkAscending(int indx ,int arr[]  ){
        if ( indx == arr.length-1){
            return true;

        }if(arr[indx] < arr[indx+1]){
           return checkAscending(indx+1,arr);
            

        }else{
            return false;
        }
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of the array: ");
        int size = sc.nextInt();
        int arr[] = new int[size];
        for(int k = 0 ; k < arr.length;k++){
            arr[k] = sc.nextInt(); 
        }
        System.out.println(checkAscending(0,arr));
        sc.close();
    }
}

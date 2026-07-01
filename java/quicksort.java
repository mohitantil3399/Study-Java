import java.util.*;
public class quicksort {
    public static int partition(int arr[],int low,int high){
        int pivot = arr[high];
        int i = low-1;
        for(int j = low ; j < high ;j++){
           if( arr[j]<pivot){
            i++;
            //swapping 
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;   
           }
        }
        //swapping the pivot
         i++;
         int temp = arr[i];
         arr[i] = arr[high];
         arr[high] = temp;
         return i;
    }
    public static void quickSort(int arr[],int low,int high){
        if(low<high ){
            int pivotIdx = partition(arr, low, high);
            quickSort(arr, low, pivotIdx -1);
            quickSort(arr, pivotIdx+1, high);
            
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.print("Enter the size of the array: ");
        int Size = sc.nextInt();
        System.out.println("Enter the input of array : ");
        int arr[] = new int [Size];
        int n = arr.length;
        for ( int l = 0 ; l < n; l++){
          arr[l] = sc.nextInt(); 
        }//printing the sorted output 
        quickSort(arr, 0, n-1);
        System.out.print("The sorted array is : {");
        for ( int i = 0 ; i < n; i ++ ){
             System.out.print(arr[i]);
             if (i < n - 1) System.out.print(", ");

        }System.out.print("}");
        System.out.println();
        sc.close();
    }
}

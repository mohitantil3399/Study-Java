import java.util.*;
//time complexity = n*logn ;
public class mergeSorting {
  public static void conquer(int arr[],int si, int mid,int ei){

    int merged[] = new int [ei-si+1];
    int idx1 = si;
    int idx2 = mid+1;
    int x = 0;
    while(idx1<=mid && idx2 <= ei ){
         if(arr[idx1] <= arr[idx2]){
            merged[x++] = arr[idx1++]; 
         }else{
            merged[x++] = arr[idx2++];
         }
    } while ( idx1 <= mid ){
         merged[x++] = arr[idx1++];
    }while(idx2 <= ei ){
        merged[x++] = arr[idx2++];
    }
    for(int i = 0  ; i < merged.length ; i++ ){
        arr[si+i] = merged [i];
    }
  }  
public static void divide(int arr[], int si, int ei  ){
 if ( si >= ei){
    return;
 }
    int mid = si +(ei-si)/2;//mid index calculations 
    divide(arr, si, mid);
    divide(arr, mid+1, ei);
    conquer(arr, si,mid, ei);
}
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter size of the array: ");
    int size = sc.nextInt();
    int arr[]= new int [size];
    int n = arr.length;
    System.out.println("Enter the array : ");
    for(int k = 0; k<n;k++){
        arr[k] = sc.nextInt(); 
    }
    divide(arr, 0, n-1);
    //print 
    System.out.print("The sorted array is : ");
    for(int i = 0 ; i < n ; i++){
        System.out.print(+arr[i]+" , ");
    }
    System.out.println();
    sc.close();
}
}

public class insertionsorting {
    public static void printArray(int arr[]){
        for(int i = 0 ; i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }System.out.println();
    }
    public static void main(String[] args) {
        int arr [] = {9,8,99,89,87,878,987,697,988,808,88,837,906,98,7790,890};
       //insertion sorting 
        for(int i = 0;i<arr.length;i++){
            int current = arr[i];
            int j = i-1;
            while(j>=0 && current < arr[j] ){
                //kep swapping
                arr[j+1] = arr[j];
                j--;
            
            }arr[j+1 ] = current;
        }
        printArray(arr);
    }
}

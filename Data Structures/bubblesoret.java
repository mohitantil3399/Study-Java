public class bubblesoret {
    public static void printArray(int arr[]){
    for(int i =0;i<arr.length;i++){
        System.out.print(arr[i]+" ");
    }
    }
    
    public static void main(String[] args) {
        int arr[] = {7,8,2,3,32,3,445,435,56,657,675,36,67,676};
        System.out.print("The given array is :"+ "{7,8,2,3,32,3,445,435,56,657,675,36,67,676}");
        System.out.println();
        //bubble sorting technique to arrange values in ascending order
        for( int j = 0;j<arr.length-1;j++){
        for(int i = 0;i<arr.length-j-1;i++){
            if(arr[i]>arr[i+1]){
                //swapping of elements
                int temporary = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temporary;

            }
        }
        }
        System.out.println();
        System.out.println("The sorted array is : ");
        printArray(arr);
    }
}

public class selectionsorting {
     
    public static void printArray(int arr[]){
    for(int i =0;i<arr.length;i++){
        System.out.print(arr[i]+" ");
    }
    }
    
    public static void main(String[] args) {
        int arr[] = {7,8,2,3,32,3,445,435,56,657,675,36,67,676};
        //slection sorting technique to arrange values in ascending order
        for( int j = 0;j<arr.length-1;j++){
            int smallest = j;
        for(int i = j+1;i<arr.length;i++){
            if(arr[i]<arr[smallest]){
                smallest = i ;
            } 
           }   //swapping of elements
                int temporary = arr[smallest];
                arr[smallest] = arr[j];
                arr[j] = temporary;

            
        
        }printArray(arr);
    }
}




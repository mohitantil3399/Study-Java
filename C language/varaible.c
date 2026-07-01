# include<stdio.h>
int main(){
    printf("Enter the number 1 : ");
    int largest;
    scanf("\n%d", & largest);
    int nums ;
    for(int i = 2; i <=10;i++){
        printf("\n Enter the number %d :  ", i );
         scanf("%d",& nums);
         if(nums > largest){
            largest = nums ;
         }
    }printf("The largest number is = %d", largest );
    // alternatively 
    int arr[10 ] = { 12,3123,3133,123,873,423,442,323,443,3443};
    for ( int i = 0 ; i < 10 - 1 ; i ++){
        for ( int j = 0 ; j< 10 -i-1; j++ ){
            if(arr[j]<arr[j+1]){
                // swapp 
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp ; 
            }
     }
     }int large = arr[0];
         printf ("\nThe largest number is %d : ", large);
     return 0;
    }
   
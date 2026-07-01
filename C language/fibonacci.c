#include<stdio.h>
int printFibonacciSeries(int n ){
    // n is total number of terms 
    if(n==0){
        return 0 ;
    }if(n==1){
        return 1 ;
    }
    int fib = printFibonacciSeries(n-1)+printFibonacciSeries(n-2);
    return fib;
}
int main (){
    int n ;
    printf("Enter the number of terms: ");
    scanf("%d",&n);
    printf("The fibonacci series is : ");
    for(int i = 0; i < n; i++) {
    printf("%d", printFibonacciSeries(i));
    if (i < n - 1) {
        printf(" , ");
    }
}
    return 0;
}
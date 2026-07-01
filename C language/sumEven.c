#include<stdio.h>
int main(){
    int sum = 0;
    int i = 0;
    while(i<101){
        if(i%2==0){
            sum = i+sum;
        }
        i++;
    }

    printf("The sum of even numbers between 1 and 100 : %d",sum);
    return 0;
}
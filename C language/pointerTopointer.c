#include<stdio.h>
int main(){
    int a = 10;
    int *p = &a;
    int **q = &p;
    printf("The value of double pointer is : %d",**q);
    return 0;
}
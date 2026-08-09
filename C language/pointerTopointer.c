#include<stdio.h>
int main(){
    int a = 10;
    int *p = &a;
    int **q = &p;
    printf("The integer address of double pointer is : %d\n",q);
    printf("The address of double pointer is : %s\n",q);
    printf("The value of double pointer is : %d",**q);
    return 0;
}
#include<stdio.h>
int main(){
    int a[5] = {1,23,4,5,6};
    int *p1 = &a[1];
    int *p2 = &a[4];
    printf("The differnce between the elements is :%d ",*p2-*p1);
    return 0;

}

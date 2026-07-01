// write a program to reverse a numbeer 
#include<stdio.h>
#include<string.h>

int main(){
    char numstring[90],original[90];
    int length , i;
    printf("Enter your number: ");
    scanf("%s",numstring);
    strcpy(original,numstring);
    length = strlen(numstring);
    for(i = 0;i < length/2;i++){
        char temp = numstring[i];
        numstring[i] = numstring[length-1-i];
        numstring[length-1-i]= temp;
    }
    printf("\nThe reversed number of %s is : %s ",original,numstring);
    return 0;
}
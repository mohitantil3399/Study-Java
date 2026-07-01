//Write a program to convert a given decimal number into binary number.
#include <stdio.h>

int main() {
    int decimal, originalDecimal;
    int binary[32]; 
    int i = 0;      

    printf("Enter a decimal number: ");
    scanf("%d", &decimal);
    
    originalDecimal = decimal; 

    // Handle the case of 0 separately
    if (decimal == 0) {
        printf("Binary equivalent of 0 is: 0\n");
        return 0;
    }

    // Convert decimal to binary by repeatedly dividing by 2
    while (decimal > 0) {
        binary[i] = decimal % 2; 
        decimal = decimal / 2;
        i++;
    }

    printf("Binary equivalent of %d is: ", originalDecimal);
    for (int j = i - 1; j >= 0; j--) {
        printf("%d", binary[j]);
    }
    printf("\n");

    return 0;
}

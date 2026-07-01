//Problem: Write a program to find sum of digits of a number.

#include <stdio.h>

int main() {
    int num, originalNum;
    int digit, sum = 0;

    printf("Enter an integer: ");
    scanf("%d", &num);

    originalNum = num; 

    // Make the number positive if it's negative
    if (num < 0) {
        num = -num;
    }

    // Loop to extract and sum digits
    while (num != 0) {
        digit = num % 10; // Get the last digit
        sum += digit;     // Add it to the sum
        num /= 10;        
    }

    printf("Sum of digits of %d is: %d\n", originalNum, sum);
    
    return 0;
}


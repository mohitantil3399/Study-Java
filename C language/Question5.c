//Write a program to find the largest number out of ten numbers (for-statement).

#include <stdio.h>
#include <limits.h> 
int main() {
    int i, num;
    int largest = INT_MIN; // Initialize largest to the smallest possible integer

    printf("Enter 10 numbers:\n");

    // Use a for-loop to read 10 numbers
    for (i = 1; i <= 10; i++) {
        printf("Number %d: ", i);
        scanf("%d", &num);
        
        // Check if the current number is larger than the current largest
        if (num > largest) {
            largest = num;
        }
    }
    printf("The largest number is: %d\n", largest);

    return 0;
}



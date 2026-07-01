// Write a program to generate pyramid of numbers.

#include <stdio.h>

int main() {
    int rows, i, j, space;

    printf("Enter the number of rows for the number pyramid: ");
    scanf("%d", &rows);

    for (i = 1; i <= rows; i++) {
        // Print leading spaces
        for (space = 1; space <= rows - i; space++) {
            printf("  "); // Two spaces for formatting
        }
        // Print increasing numbers (from 1 to i)
        for (j = 1; j <= i; j++) {
            printf("%d ", j);
        }
        // Print decreasing numbers (from i-1 down to 1)
        for (j = i - 1; j >= 1; j--) {
            printf("%d ", j);
        }
        
        printf("\n"); // Move to the next line
    }
    
    return 0;
}


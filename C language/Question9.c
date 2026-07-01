//Write a program to find the Fibonacci series using recursion.

#include <stdio.h>

int fibonacci(int n) {
    if (n == 0) {
        return 0;
    }
    else if (n == 1) {
        return 1;
    }
    else {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

int main() {
    int n, i;
    printf("Enter the number of terms for Fibonacci series: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Error: Number of terms must be positive.\n");
    } else {
        printf("Fibonacci Series (%d terms):\n", n);
        for (i = 0; i < n; i++) {
            printf("%d ", fibonacci(i));
        }
        printf("\n");
    }
    
    return 0;
}

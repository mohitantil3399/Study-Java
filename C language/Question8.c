//Write a program to find the factorial of a given number using recursion.
#include <stdio.h>

// Using long long to handle larger factorial values
long long factorial(int n) {
    // Base case:
    if (n == 0 || n == 1) {
        return 1;
    }
    else {
        return n * factorial(n - 1);
    }
}

int main() {
    int num;
    printf("Enter a non-negative integer: ");
    scanf("%d", &num);
    if (num < 0) {
        printf("Error: Factorial is not defined for negative numbers.\n");
    } else {
        printf("Factorial of %d = %lld\n", num, factorial(num));
    }
    
    return 0;
}

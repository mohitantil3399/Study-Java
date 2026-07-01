// Write a program to find the largest of three numbers. (if-then-else).

#include <stdio.h>

int main() {
    int n1, n2, n3, largest;

    printf("Enter three numbers: ");
    scanf("%d %d %d", &n1, &n2, &n3);

    // Using nested if-else as requested
    if (n1 >= n2) {
        if (n1 >= n3) {
            largest = n1;
        } else {
            largest = n3;
        }
    } else {
        if (n2 >= n3) {
            largest = n2;
        } else {
            largest = n3;
        }
    }
    printf("The largest number is: %d\n", largest);
    return 0;
}


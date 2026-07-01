//Write a program to multiply two matrices.
#include <stdio.h>

int main() {
    int A[3][3] = {1, 3, 4, 2, 8, 6, 0, 4, 6};
    int B[3][3] = {3, 4, 6, 6, 3, 9, 7, 5, 4};
    int result[3][3];

    // Matrix multiplication: result[i][j] = sum of A[i][k] * B[k][j]
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            result[i][j] = 0;
            for (int k = 0; k < 3; k++) {
                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    // Print the result matrix
    printf("Multiplied matrix is:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d\t", result[i][j]);
        }
        printf("\n");
    }

    return 0;
}
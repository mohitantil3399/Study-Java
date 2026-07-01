#include <stdio.h>
#include <stdlib.h>  // for malloc, calloc, realloc, free

int main() {
    int *arr;
    int i;

    // 1. malloc example
    arr = (int*) malloc(5 * sizeof(int));  // allocate space for 5 integers
    if (arr == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }
    printf("Using malloc (garbage values):\n");
    for (i = 0; i < 5; i++) {
        printf("%d ", arr[i]);  // uninitialized values
    }
    printf("\n");

    // 2. calloc example
    int *arr2 = (int*) calloc(5, sizeof(int));  // allocate and initialize to 0
    if (arr2 == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }
    printf("Using calloc (initialized to zero):\n");
    for (i = 0; i < 5; i++) {
        printf("%d ", arr2[i]);  // all zeros
    }
    printf("\n");

    // 3. realloc example
    arr2 = (int*) realloc(arr2, 10 * sizeof(int));  // resize to hold 10 integers
    if (arr2 == NULL) {
        printf("Reallocation failed!\n");
        return 1;
    }
    printf("After realloc (size increased to 10):\n");
    for (i = 0; i < 10; i++) {
        printf("%d ", arr2[i]);  // first 5 are preserved, rest may be garbage
    }
    printf("\n");

    // 4. free example
    free(arr);   // release malloc memory
    free(arr2);  // release calloc/realloc memory
    arr = NULL;  // good practice
    arr2 = NULL;

    printf("Memory freed successfully.\n");

    return 0;
}
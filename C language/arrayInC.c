#include <stdio.h>
#include <stdlib.h>

int main() {
    int size;
    printf("Enter size of the array: ");
    scanf("%d", &size);

    int* arr = (int*)malloc(size * sizeof(int));
    
    if (arr == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }
    for(int j = 0; j < size ; j++){
        printf("Enter the values :%d");
        scanf("%d",&arr[j]);
    }

    for (int i = 0; i < size; i++) {
        printf("array[%d] = %d\n", i, arr[i]);  // uninitialized values
    }

    free(arr);  // good practice to free allocated memory
    return 0;
}

//Write a program using arrays to find the largest, second largest and third largest no. out of given numbers
#include <stdio.h>

int main() {
    int size;
    printf("Enter the number of elements: ");
    scanf("%d", &size);
    int arr[size]; 

    // Input elements
    printf("Enter %d integers:\n", size);
    for (int i = 0; i < size; i++) {
        scanf("%d", &arr[i]);
    }
    // Bubble Sort for ascending sorting 
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    // Print largest number
    printf("\n The largest number is: %d\n", arr[size - 1]);
     printf("\n The second largest number is: %d\n", arr[size - 2]);
      printf("\n The third largest number is: %d\n", arr[size - 3]);

    return 0;
}

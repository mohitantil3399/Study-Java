#include <stdio.h>
#include <string.h> // Optional if not using strcat()

int main() {
    // str1 must be large enough to hold both str1 and str2
    char str1[200], str2[100];

    printf("Enter the first string: ");
    scanf("%s", str1);  // Use %s for string input
    printf("Enter the second string: ");
    scanf("%s", str2);  // Use %s for string input

    int i = 0, j = 0;

    // Find the end of the first string
    while (str1[i] != '\0') {
        i++;
    }

    // Copy characters from str2 to the end of str1
    while (str2[j] != '\0') {
        str1[i] = str2[j];
        i++;
        j++;
    }

    // Add the null terminator to the end
    str1[i] = '\0';

    printf("Concatenated string : %s\n", str1);

    return 0;
}
// Write a program to check that the input string is a palindrome or not.
#include <stdio.h>
#include <string.h>

int main() {
    char str[100];
    int left = 0;
    int right;
    int isPalindrome = 1; // creating boolean 

    printf("Enter a string: ");
    // Read the whole line, including spaces, up to 99 chars
    fgets(str, 100, stdin); 

    // Remove the newline character added by fgets
    str[strcspn(str, "\n")] = 0;

    right = strlen(str) - 1;

    // Compare characters from both ends moving inwards
    while (right > left) {
        // Note: This is case-sensitive. 'M' != 'm'
        if (str[left] != str[right]) {
            isPalindrome = 0; // Set flag to false
            break;            
        }
        left++;
        right--;
    }

    if (isPalindrome) {
        printf("'%s' is a palindrome.\n", str);
    } else {
        printf("'%s' is not a palindrome.\n", str);
    }

    return 0;
}


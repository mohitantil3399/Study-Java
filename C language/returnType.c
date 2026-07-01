#include <stdio.h>

int main() {
    int r = printf("%s", "Hello","\n");  // prints 'C' and returns number of characters printed (1)
    printf("\n%d", r);            // prints the value of r (1)
    return 0;
}

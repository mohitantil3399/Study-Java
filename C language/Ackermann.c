#include <stdio.h>

// Recursive Ackermann function
int ackermann(int m, int n) {
    if (m == 0) {
        return n + 1;
    } else if (m > 0 && n == 0) {
        return ackermann(m - 1, 1);
    } else {
        return ackermann(m - 1, ackermann(m, n - 1));
    }
}

int main() {
    int m, n;
    printf("Enter values for m : ");
    scanf("%d", &m);
     printf("Enter values for n : ");
     scanf("%d", &n);

    int result = ackermann(m, n);
    printf("Ackermann(%d, %d) = %d\n", m, n, result);

    return 0;
}
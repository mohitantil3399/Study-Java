#include <stdio.h>
#include <time.h>

int main(void)
{
    const int iterations = 100000;
    clock_t start = clock();

    for (int i = 0; i < iterations; ++i) {
        ;
    }

    clock_t end = clock();
    double elapsed_seconds = (double)(end - start) / CLOCKS_PER_SEC;

    printf("Loop iterations: %d\n", iterations);
    printf("Time taken: %.6f seconds\n", elapsed_seconds);

    return 0;
}

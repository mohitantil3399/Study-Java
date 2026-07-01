#include <stdio.h>
#include<math.h>

// Object-like macro (constant)
#define PI 3.14159

// Function-like macro
#define SQUARE(x) ((x) * (x))

// Conditional compilation
#define DEBUG 1

// More on functions 
#define cuberoot(x) (cbrt(x))

int main() {
    double radius = 5.0;
    double area = PI * SQUARE(radius);

    printf("Area of circle with radius %.2f = %.2f\n", radius, area);

    // Conditional compilation example
    #ifdef DEBUG
        printf("Debug: radius=%.2f, area=%.2f\n", radius, area);
    #endif
    float number;
    printf("Enter the number to fing cube root:");
    scanf("%f",&number);
    float result = cuberoot(number);
    printf("The cuberoot of %.4f is : %.4f",number,result);
    return 0;
}
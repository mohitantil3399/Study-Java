#include <stdio.h>

// Define an enum for traffic light states
enum TrafficLight {
    RED,
    YELLOW,
    GREEN
};

int main() {
    enum TrafficLight signal;

    // Let's test with GREEN
    printf("Enter the choice : ");
    scanf("%d",&signal);

    // Use switch to decide action
    switch(signal) {
        case RED:
            printf("Stop! The light is RED.\n");
            break;
        case YELLOW:
            printf("Get ready! The light is YELLOW.\n");
            break;
        case GREEN:
            printf("Go! The light is GREEN.\n");
            break;
        default:
            printf("Invalid signal.\n");
    }

    return 0;
}
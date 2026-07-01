//Create a structure called "Student" with members - name, age, and total marks. Write a program to input data for two students, display their information, and find the average of total marks.

#include <stdio.h>
#include <string.h> // For strcspn

// Define the structure "Student"
struct Student {
    char name[50];
    int age;
    float total_marks;
};

int main() {
    struct Student s1, s2;

    // --- Input for Student 1 ---
    printf("--- Enter details for Student 1 ---\n");
    printf("Name: ");
    fgets(s1.name, 50, stdin);
    s1.name[strcspn(s1.name, "\n")] = 0; 

    printf("Age: ");
    scanf("%d", &s1.age);

    printf("Total Marks: ");
    scanf("%f", &s1.total_marks);
    getchar(); 

    // --- Input for Student 2 ---
    printf("\n--- Enter details for Student 2 ---\n");
    printf("Name: ");
    fgets(s2.name, 50, stdin);
    s2.name[strcspn(s2.name, "\n")] = 0; 

    printf("Age: ");
    scanf("%d", &s2.age);

    printf("Total Marks: ");
    scanf("%f", &s2.total_marks);

    // --- Display Information ---
    printf("\n--- Student Information ---\n");
    printf("Student 1:\n");
    printf("\tName: %s\n", s1.name);
    printf("\tAge: %d\n", s1.age);
    printf("\tTotal Marks: %.2f\n", s1.total_marks); 

    printf("\nStudent 2:\n");
    printf("\tName: %s\n", s2.name);
    printf("\tAge: %d\n", s2.age);
    printf("\tTotal Marks: %.2f\n", s2.total_marks);

    // --- Calculate and Display Average Marks ---
    float average = (s1.total_marks + s2.total_marks) / 2.0;
    printf("\n==================================\n");
    printf("Average of total marks: %.2f\n", average);
    printf("==================================\n");

    return 0;
}

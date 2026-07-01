// Importing utility classes for input and collections
import java.util.*;

// Defining a class to represent each student
class Student {
    String name;       // Student's name
    int marks;         // Marks obtained
    double percentage; // Calculated percentage based on total marks

    // Constructor to initialize name, marks, and calculate percentage
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
        this.percentage = (marks / 90.0) * 100; // Assuming total marks are out of 90
    }

    // Overriding toString() to include percentage in output
    public String toString() {
        return name + " - " + marks + " marks (" + String.format("%.2f", percentage) + "%)";
    }
}

// Main class that handles input, sorting, and filtering
public class StudentSorter {
    public static void main(String[] args) {
        // Creating Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Creating a list to store Student objects
        List<Student> studentList = new ArrayList<>();

        // Asking user for number of students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();      // Reading number
        sc.nextLine();             // Consuming leftover newline

        // Prompting user to enter student details
        System.out.println("Enter student details (Name Marks):");

        // Loop to read each student's data
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine().trim();         // Reading full line
            int lastSpace = input.lastIndexOf(' ');      // Finding last space to split name and marks
            String name = input.substring(0, lastSpace); // Extracting name
            int marks = Integer.parseInt(input.substring(lastSpace + 1)); // Extracting marks
            studentList.add(new Student(name, marks));   // Creating and adding Student object
        }

        // Sorting students alphabetically by name (case-insensitive)
        Collections.sort(studentList, Comparator.comparing(s -> s.name.toLowerCase()));

        // Displaying sorted list
        System.out.println("\nStudents sorted alphabetically:");
        for (Student s : studentList) {
            System.out.println(s); // Uses overridden toString()
        }

        // Displaying students with marks above 80
        System.out.println("\nStudents with marks above 80:");
        boolean found = false;
        for (Student s : studentList) {
            if (s.marks > 80) {
                System.out.println(s); // Includes percentage
                found = true;
            }
            }if(!found ){
                System.out.println("NO student has got marks more than 80 ");
        }
        sc.close();
    }
}
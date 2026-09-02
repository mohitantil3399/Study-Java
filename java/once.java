import java.util.*;
    
public class once {
    public static void main (String[]args){
    Scanner  sc = new Scanner(System.in);
    System.out.println("enter either 1 or 0 :");
    
   // int n = 1 ;
   int choice;

        do {
            System.out.print("Enter 1 to input marks, or 0 to stop: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter marks out of 100: ");
                int marks = sc.nextInt();

                if (marks >= 90 && marks <= 100) {
                    System.out.println("This is a good score.");
                } else if (marks >= 60 && marks <= 89) {
                    System.out.println("This is also a good score.");
                } else if (marks >= 0 && marks <= 59) {
                    System.out.println("Well tried.");
                } else {
                    System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                }
            } else if (choice != 0) {
                System.out.println("Invalid input! Please enter 1 or 0.");
            }
        } while (choice != 0);

        System.out.println("Marks do not matter, but our efforts do. 🌟");
        sc.close();
    }
}


   
   
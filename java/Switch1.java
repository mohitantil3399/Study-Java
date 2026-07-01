import java.util.Scanner;

public class Switch1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input coefficients
        System.out.print("Enter coefficient a: ");
        double a = sc.nextDouble();
        System.out.print("Enter coefficient b: ");
        double b = sc.nextDouble();
        System.out.print("Enter coefficient c: ");
        double c = sc.nextDouble();

        // Check for valid quadratic equation
        if (a == 0) {
            System.out.println("This is not a quadratic equation.");
            
        }

        double discriminant = b * b - 4 * a * c;
        int caseType = (discriminant > 0) ? 1 : (discriminant == 0) ? 2 : 3;

        switch (caseType) {
            case 1:
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                System.out.println("Two distinct real roots:");
                System.out.printf("Root 1 = %.2f\n", root1);
                System.out.printf("Root 2 = %.2f\n", root2);
                break;

            case 2:
                double root = -b / (2 * a);
                System.out.println("One real repeated root:");
                System.out.printf("Root = %.2f\n", root);
                break;

            case 3:
                double realPart = -b / (2 * a);
                double imagPart = Math.sqrt(-discriminant) / (2 * a);
                System.out.println("Complex roots:");
                System.out.printf("Root 1 = %.2f + %.2fi\n", realPart, imagPart);
                System.out.printf("Root 2 = %.2f - %.2fi\n", realPart, imagPart);
                break;

            default:
                System.out.println("Unexpected case.");
        }

        sc.close();
    }
}

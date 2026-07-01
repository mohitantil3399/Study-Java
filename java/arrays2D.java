import java.util.Scanner;
public class arrays2D{
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of rows: ");
    int rows = sc.nextInt();
    System.out.print("Enter the number of columns : ");
    int columns = sc.nextInt();
    int[][]matrix = new int[rows][columns];
    //input i for rows and j for columns
    System.out.println("Enter the input values: ");
    for ( int i = 0; i < rows;i++){
        for ( int j = 0 ; j<columns;j++){
            matrix[i][j] = sc.nextInt();
        }
    }
        //output
        System.out.println("Output: ");
        for ( int i = 0; i < rows;i++){
        for ( int j = 0 ; j<columns;j++){
            System.out.print(matrix[i][j]+"  ");
        }System.out.println();
    }sc.close();
}
}
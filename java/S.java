import java.util.*;

public class S{// printing S pattern 
public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the String : ");
    String a = sc.next();
    String matrix [][] = new String[14][10];
    matrix [1][5] = a;
    matrix [1][6] = a;
    matrix [2][4] = a;
    matrix [2][7] = a;
    matrix [3][3] = a;
    matrix [3][8] = a;
    matrix [4][3] = a;
    matrix [5][4] = a;
    matrix [6][5] = a;
    matrix [7][6] = a;
    matrix [8][7] = a;
    matrix [9][7] = a;
    matrix [10][7] = a;
    matrix [10][3] = a;
    matrix [11][3] = a;
    matrix [11][6] = a;
    matrix [12][4] = a;
    matrix [12][5] = a;
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == a){
            System.out.print(a );
            Thread.sleep(100);
        }
        else{
            System.out.print("  "); // print space for empty cells
        }
    }
    System.out.println(); // move to next line
}
    
sc.close();
    
}
}
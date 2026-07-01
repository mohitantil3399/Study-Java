import java.util.Scanner;
public class arrays2D2 {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows : ");
        int r = sc.nextInt();
        System.out.print("Enter number of columns : ");
        int c = sc.nextInt();
        int[][]matrix = new int[r][c];
        //input material of matrix
        System.out.println("Enter values as input: ");
        for (int i = 0 ; i <r;i++){
            for ( int j=0;j<c;j++){
                matrix[i][j] = sc.nextInt();
            }
        } System.out.println("THe entered matrix is : ");
               for (int i = 0 ; i <r;i++){
            for ( int j=0;j<c;j++){
              //output matrix 
               System.out.print(matrix[i][j]+" ");
            }System.out.println();
        }
        //output transpose matrix
        System.out.println("The transpose matrix is :");
        for ( int j = 0 ;j<c;j++){
            for (int i = 0 ; i < r ;i++){
                System.out.print(matrix[i][j]+"  ");
            } System.out.println();
        }
         sc.close();
    }

}

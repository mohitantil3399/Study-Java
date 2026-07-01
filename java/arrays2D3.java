import java .util.Scanner;
public class arrays2D3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int r = sc.nextInt();
        System.out.print("Enter number of columns : ");
        int c = sc.nextInt();
        int matrix[][] = new int [r][c];
        System.out.println("Enter input values:");
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j<c ; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println("The spiral order matrix is : ");
        int rowstart = 0;
        int rowend = r-1;
        int columnstart = 0;
        int columnend = c-1;
        //to print spiral order matrix 
        while(rowstart<=rowend && columnstart<=columnend){
            //1
            for(int col = columnstart; col <= columnend ; col++){
                System.out.print(matrix[rowstart][col]+" ");

            }rowstart++;
            //2
            for (int row = rowstart; row<=rowend ;row++){
                System.out.print(matrix[row][columnend]+" ");
               
            }columnend--;
            //3
            for( int col = columnend; col>=columnstart;col--){
                System.out.print(matrix[rowend][col]+" ");

            }rowend--;
            //4
            for( int row = rowend;row>=rowstart;row--){
                System.out.print(matrix[row][columnstart]+" ");
            }columnstart++;
            System.out.println();
        }
           sc.close();
    }
}

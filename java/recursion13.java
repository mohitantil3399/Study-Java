import java.util.Scanner;
public class recursion13 {
    public static int countPaths(int n,int m , int i , int j ){
        if ( i == n || j == m ){
            return 0;
        }if ( i == n-1 && j == m-1){
            return 1; 
        }//moving downwards 
        int down = countPaths(n, m, i+1, j);
        //moving right 
        int right =countPaths(n, m, i, j+1);
        return  right +down;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System .in);
        System.out.print("Enter number of rows: ");
        int n = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int m = sc.nextInt();
       int totalPaths = countPaths(n, m, 0, 0);
       System.out.println(totalPaths);
       sc.close();
    }
}

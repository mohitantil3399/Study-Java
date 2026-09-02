import java.util.Scanner;
public class recursion14 {
    public static int countTiles(int n, int m  ){
        if ( n == m){
            return 2;// in this case 2 ways are only possible ,either horizontal placement or vertical placemnt
        }if (n < m ){
            return 1 ; // only one case is possible and that is horizontal  , for tile size is (1*m)
        }
        // vertical placements 
        int verPlacements = countTiles(n-m, m);
        //horizontal placements 
        int horPlacements = countTiles(n-1, m);
        return verPlacements+horPlacements;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows : ");
        int n = sc.nextInt();
        System.out.print("Enter number of columns : ");
        int m = sc.nextInt();
        System.out.println("The dimension of each tile is: 1*"+m);
        System.out.println("The dimension of floor is : "+m*n);
        long TotalCount = countTiles(n, m);
        System.out.println("The total number of ways to place the tiles are : "+TotalCount);
     sc.close();
    }
}

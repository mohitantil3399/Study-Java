import java.util.Scanner;
public class towerOfHanoi {
    public static void printTower(int n , String src , String helper,String dest){
        if(n ==1 ){
            System.out.println("The disk "+n+" is tranferred from "+ src+" to "+dest);
            return;
        }printTower(n-1, src, dest,helper);
        System.out.println("The disk"+n+" is tranferred from "+ src+" to "+dest);
        printTower(n-1, helper, src, dest);
    }
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        System.out.print("Enter the number of disks: ");
        int n = ms.nextInt();
        printTower(n, "Source", "Helper", "Destination");
        ms.close();
    }
}

import java.util.LinkedList;
import java.util.Scanner;

public class Llist4 {
    public static void main(String[] args) {
        LinkedList<Integer> List = new LinkedList<>();
      List.add(1);
      List.add(2);
      List.add(3);
      List.add(4);
      List.add(5);
      List.add(6);
      List.add(7);
      List.add(8);
      List.add(9);
      System.out.println(List);
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the number of element from last you want to delete : ");
      int n = sc.nextInt();
      int index = List.size()-n;
      List.remove(List.get(index));
      System.out.println(List);
      sc.close();
    }
}

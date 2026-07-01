import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
public class Hashing1 {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(4);
        set.add(9);
        set.add(3);
        System.out.print(set);
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number you want to search : ");
        int x = sc.nextInt();
        System.out.println("set contains :\n"+set.contains(x));
        Iterator i =  set.iterator();
       while(i.hasNext()){
        System.out.println(i.next());
        sc.close();
        
       }
    }
}

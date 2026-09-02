import java.util.LinkedList;

public class Llist3{
    public static void main(String[] args) {
        LinkedList<Integer> List = new LinkedList<>();
          List.add(1);
          List.add(2);
          List.add(3);
          List.add(4);
          List.add(5);
          List.add(6);
          System.out.println("The original list is : ");
          System.out.print(List);
          for(int i = 0; i <= List.size()-1;i =2+i){
            // swapping the adjacent
            int temp = List.get(i);
            List.set(i, List.get(i+1));
            List.set(i+1, temp); 
          }System.out.println();
          System.out.println("The swapped List is : ");
          System.out.print(List);
    }
}
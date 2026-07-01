import java.util.*;
public class Llistcollectionsframework {
    // lets use the pre defined packages of collection framework to make linked lists.


    public static void main(String[] args) {
        LinkedList<String> List = new LinkedList<String>();
        List.addFirst("a");
        List.addFirst("is");
        List.addFirst("this");
        System.out.println(List);
        List.add("List");
        List.add("for basics");
          for(int i = 0 ; i <List.size(); i ++){
         System.out.print(List.get(i)+"->");// . get to fetch elements
          }// this is how we iterate over the list 
          System.out.println("Null");
        List.remove(1);
        System.out.println(List.get(3));
    }
}

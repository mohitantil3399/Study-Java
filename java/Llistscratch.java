//main class is Llistscracth
import java.util.Scanner;
public class Llistscratch {
    //creating head property
    Node head ;
    private int size ;
    Llistscratch(){// constructor 
        this.size = 0;
    }
 // creating a node class
class Node {
    // properties of node class 
    String data;
    Node next;
    Node(String data){ // its a node constructor with initialized variable 
        // creating objects using this 
        this.data = data;
        this.next = null;
        size ++;

    } 
} //add first
  public void addFirst(String data){
    // newNode object using node constructor
        Node newNode = new Node(data);
        if(head == null ){ // check if head of list is null
        head = newNode; // if null then head is the newnode
        return;
        }newNode.next = head; // next link is null then 
        head = newNode;// added newnode is new head
    }//add last
    public void addLast(String data){
        Node newNode = new Node(data);
        if(head == null ){
        head = newNode;
        return;
    } Node currnode = head ;// traverse on the curent node using a new currnode object 
    while(currnode.next != null){// if currnode se agla node  is not equal to null
        currnode = currnode.next;// currnode ko alga currnode bnado
    }  currnode.next = newNode;
}
// printing the list
      public void printList(){
        if(head == null ){//agar head hi null h to list mein kuch h hi nhi 
            System.out.println("This list is empty .");
            return;
        }
        Node currnode = head ;
    while(currnode!= null){  
        System.out.print(currnode.data+" -> ");
       currnode = currnode.next;
    } 
     System.out.println("NULL");
      
      }// delete first 
      public void deleteFirst(){
        // corner case ;
        if(head == null){
            System.out.println("The list is empty.");
            return;
        }size--;
        // otherwise newhead ,list ka agla node ban jayegA 
        head = head.next;
      }
      //delete last 
      public void deleteLast(){
         // corner case ;
        if(head == null){
            System.out.println("The list is empty.");
            return;
        }size--;
        if(head.next == null){// agar second last null h to 
            head = null ; 
            return;
        }//consider two variables to traverse over the list
          Node secondlast = head;// consider secondlast is the head
          Node lastnode = head.next;
          while(lastnode.next != null){
            lastnode = lastnode.next;
            secondlast = secondlast.next;
          }
          secondlast.next = null; // second last pe pahunchte hi usse agla null ho jayega
      }
      public int getSize(){
        return size;
      }
      public void reverseIterate(){
        if(head == null || head.next == null){
            return;
        }
        Node prevNode = head;
        Node currNode = prevNode.next;
        while (currNode!= null ) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;
            // updating the parameters
            prevNode = currNode;
            currNode = nextNode;  
        } 
          head.next = null;// purana head tha , uska agla null ho jayega
          head = prevNode;// reversed list ka prevNode new head banjayega.

      }

      // using recursive approach to do reversing of linked lists 
      public Node recursiveReversing(Node head){
        if ( head == null || head.next == null ){
            return head ;
        }  
        Node newHead = recursiveReversing(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
      }
public static void main(String[] args) {
    Llistscratch List =  new Llistscratch();//object of Node constructor
    // adding data in the first node
    List.addFirst("a");
    // adding data before first node
    List.addFirst("is");
    //printing the list
    List.printList();
    // creating  a last node & adding data to the last node 
    List.addLast("List");
    List.addLast("made using linked list");
    List.printList();
    // using Scanner class to take a string input ;
    Scanner sc = new Scanner(System.in);
    System.out.print("Input your string here : ");
    String n = sc.nextLine();
    List.addLast(n);
    //printing
    List.printList();
    // adding data in a node before in the previous list
    List.addFirst("This ");
    //final output
    List.printList();

    // using delete methods 
     List.deleteFirst();
     List.printList();

     List.deleteLast();
     List.printList();

     // getting size
    int size =  List.getSize();
    System.out.println("The size of the list is : "+size);

    // adding again to check getSize is working properly 
    List.addLast("using linked list");
    List.addFirst("this");
    List.printList();;
    System.out.println("The size of the list is : "+List.getSize());
    System.out.println();
    // making a list
    Llistscratch List1 = new Llistscratch();
    List1.addLast("1");
    List1.addLast("2");
    List1.addLast("3");
    List1.addLast("4");
    List1.addLast("5");
    List1.addLast("6");
    List1.addLast("7");
    System.out.println("The given list is : ");
    List1.printList();
    System.out.println();
    System.out.println( " The reversed list is as follows :");
    List1.reverseIterate();
    List1.printList();
    System.out.println();

   Llistscratch List2 = new Llistscratch();
   List2.addFirst("This");
   List2.addFirst("List ");
   List2.addFirst("is ");
   List2.addFirst("reversed");
   List2.addFirst("by");
   List2.addFirst("recursive call ");
System.out.println("The given list is: ");
   List2.printList();
System.out.println();
System.out.println("The reversed list is as follows : ");
    List2.head =  List2.recursiveReversing(List2.head);
    List2.printList();
    sc.close();
}
}

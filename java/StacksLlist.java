import java.util.Scanner;
public class StacksLlist {

    static class Node{
        int data;
        Node next;
        public Node(int data){// constructor 
        this.data = data;
        next = null;   
        }
    }

    static class Stacks{
        public static Node head ;
       // checking if stack is empty 
       public  boolean isEmpty(){
         return head == null;
         }
         public  void push(int data ){
            Node newnode = new Node(data);//object
            if( isEmpty() ){//if its empty
            head = newnode ;
          return;
            }
            newnode.next = head;// newnode will create a node , uska next purane ko point karega 
            // head ab newhead ban jayega
            head = newnode;
         }
         public  int pop(){
            // base case :
            if(isEmpty()){
                return -1;
            }
           int top = head.data;
           // if top to be deleted 
           head = head.next;
           return top ;// value of top is changed by changing the head
          
         }
         public  int peek(){
            // base case :
            if(isEmpty()){
                return -1;
            }
            return head.data;// peek means dekho kya data h top walle stack index mein
         }
       }
    
    public static void main(String[] args) {
        // stack constructor :
           Stacks newStack = new Stacks();//creating an object for stacks class;
           newStack.push(1);// stack mein sabse upar element  add kia 
           newStack.push(2);
           newStack.push(3);
           newStack.push(4);
           System.out.println("The stack data sturucture is as follows: ");
           while(!newStack.isEmpty()){
            System.out.println(newStack.peek());// peek for printing stack
            // pop for deleting
            newStack.pop();//if pop is not used , it will keep printing 1st node , until isEmpty is reached 
           }
           Stacks Stacks2 = new Stacks();//object 2
           System.out.println();// a line gap

           Scanner sc = new Scanner(System.in);
           System.out.print("Enter the number whose table you want : ");
           int n = sc.nextInt();

           for (int i = 10; i >=1; i --){
            Stacks2.push(i*n);
           }
           
           System.out.println("The table of "+n+" is :");
           while(!Stacks2.isEmpty()){
            System.out.println(Stacks2.peek());
            Stacks2.pop();
           }
           sc.close();
    }
}

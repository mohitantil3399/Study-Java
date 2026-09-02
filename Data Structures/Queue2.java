public class Queue2 {

    static class Queue {
         public  static Node head = null;// node variable head and tail
         public static Node tail = null;
        
       static class Node{//Node class
            int data;
            Node next ;

              Node(int data){// constructor of node class
            this.data = data;
            next = null;
        }
    }
        
        public static boolean isEmpty(){
           return head == null & tail == null;// condition for full is not required in linkedlists
        }
        //enqueue
        public static void add(int data){
            Node newnode = new Node(data);
        // base case , if list is already empty :
        if ( tail == null){
            head = tail = newnode;
            return;
        }
        tail.next = newnode;// insertion is always at the end 
        tail = newnode;
        }
     // dequeue
     public static int remove(){
        if(isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        int front = head.data;
        // what is there is only 1 element in the list ?
        if(head == tail ){
            tail = null ; // removed the only element
        }
        head = head.next;
        return front;
     }
    // peek 
    public static int peek(){
        if(isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        return head.data;
    }

    }
    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(9);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
      System.out.println("an elemnt is removed: " +q.remove());
       System.out.println("an elemnt is removed: " +q.remove());
        System.out.println("an elemnt is removed: " +q.remove());
        System.out.println();

        System.out.println("The queue list is : ");
        // prin ting hte list 
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
        
    }
}

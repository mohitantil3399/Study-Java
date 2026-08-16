public class lList {
    Node head;
    class Node{
        Node next;
        String data;
        // Constructor of the Node class that takes the data
        Node(String data){
            this.data = data;
            this.next = null;//the initial node has no next nodes 
        }
    }
        public void createNode(String data){
            Node newNode = new Node(data);
            //check if head node is null
            if(head == null){
                head = newNode;
                return;
            }
            newNode.next = head; // next link is null then 
            head = newNode;// added newnode is new head
        }
        //printing function
         public void printList(){
            if(head == null ){//agar head hi null h to list mein kuch h hi nhi 
            System.out.println("This list is empty .");
            return;
        }
            Node curNode = head;
            while(curNode.next != null){
                System.out.print("["+curNode.data+"]"+" -> ");
                curNode = curNode.next ;//update curren node to next node
            }
        }
    public static void main(String[] args) {
        lList list = new lList();
        
        list.createNode("Hello");
        list.createNode("I");
        list.createNode(" am");
        list.createNode("Puppy");
        list.createNode("happy");
        list.createNode("to go");
        list.createNode("for a walk");
        //printing the list 
        list.printList();
        
    }
}

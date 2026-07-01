//circular queue using array
public class Queue1 {
   static class Queue {
       static int arr[];
       static int size;
       static int front = -1;
       static int rear = -1;


       Queue(int size) {
           this.size = size;
           arr = new int[size];
       }


       public static boolean isEmpty() {
           return rear == -1 && front == -1;
       }


       public static boolean isFull() {
           return (rear+1)%size == front;
       }


       public  void add(int data) {
           if(isFull()) {
               System.out.println("Overflow as the queue is already full");
               return;
           }
           //if it's the 1st element
           if(front == -1) {
               front = 0;
           }


           rear = (rear + 1)%size;
           arr[rear] = data;
       }


       public  int remove() {
           if(isEmpty()) {
               System.out.println("empty queue");
               return -1;
           }
           int res = arr[front];


           //if only 1 element is present
           if(front == rear) {
               front = rear = -1;
           } else {
               front = (front+1)%size;
           }


           return res;
       }


       public  int peek() {
           if(isEmpty()) {
               System.out.println("empty queue");
               return -1;
           }
          
           return arr[front];
       }
   }
   public static void main(String args[]) {
       Queue q = new Queue(5);
       q.add(1);
       q.add(2);
       q.add(3);
       q.add(4);
       q.add(5);
       System.out.println("The removed element is :"+q.remove());
       q.add(6);
       System.out.println("The removed element is :"+q.remove());
       q.add(7);
       System.out.println("The removed element is :"+q.remove());
       q.add(8);
        System.out.println("The removed element is :"+q.remove());
         System.out.println("The removed element is :"+q.remove());
         q.add(10);
         q.add(11);
         q.add(12);
         q.add(13);

       while(!q.isEmpty()) {
           System.out.println(q.remove());
       }
   }
}




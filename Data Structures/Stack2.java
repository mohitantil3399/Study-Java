import java.util.Stack;
public class Stack2 {
    public static void pushAtBottom(int data , Stack<Integer> S){
        if(S.isEmpty()){
            S.push(data);
            return;
        }int top = S.pop();// top walle element ko pop karo ,use ek variable top mein dal do
        pushAtBottom(data, S);
        S.push(top);// sab remove karne baad , top ko add kardo;
    }
    public static void reverse(Stack<Integer> S){
        if(S.isEmpty()){
            return;
        }
        int top = S.pop();
        reverse(S);
        pushAtBottom(top,S);//calling push at bottom to making a reverse stack ,by pushing elements at bottom
    }
    public static void main(String[] args) {
        Stack<Integer> S = new Stack<>();
        S.push(1);
        S.push(2);
        S.push(3);
        S.push(4);
        reverse(S);
        System.out.println("The reversed stack is : ");
        while(!S.isEmpty()){
            System.out.println(S.peek());
            S.pop();
        }
    }
}


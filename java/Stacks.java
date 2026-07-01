import java.util.Stack;
public class Stacks {
    public static void pushAtBottom(int data , Stack<Integer> S){
        if(S.isEmpty()){
            S.push(data);
            return;
        }int top = S.pop();// top walle element ko pop karo ,use ek variable top mein dal do
        pushAtBottom(data, S);
        S.push(top);// sab remove karne baad , top ko add kardo;
    }
    public static void main(String[] args) {
        Stack<Integer> S = new Stack<>();
        S.push(1);
        S.push(2);
        S.push(3);
        pushAtBottom(4,S);
        while(!S.isEmpty()){
            System.out.println(S.peek());
            S.pop();
        }
    }
}

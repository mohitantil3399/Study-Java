public class BST2 {
      static class Node{
        int data;
        Node left;
        Node right;
        Node(int data ){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
        public static Node insert(Node root, int val){
            if (root == null){
              root = new Node(val);// constructor for new node
                return root;
            }else if (root.data > val){
                // left subtree
                root.left = insert(root.left, val);
            }else {
                root.right = insert(root.right, val);
            }
            return root;
      }
      public static Node inorder(Node root){
        if(root == null){
            return null;
        }inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
        return root;
      }
         public static void printInRange(Node root, int X , int Y){
            //base case 
            if(root == null){
                return;
            }
            //case1 
            if(root.data>= X & root.data<=Y){
                printInRange(root.left, X, Y);
                printInRange(root.right, X, Y);
                System.out.print(root.data+" ");
            }
            //case 2
            else if (root.data >= Y){
                printInRange(root.left, X, Y); 
            }
            // case 3 
            else {
                printInRange(root.right, X, Y);
            }
         }
    public static void main(String[] args) {
        int value[] = {8,5,10,3,4,1,11,14,6};
        Node root = null;
        for(int i =0;i<value.length;i++){
       root = insert(root,value[i]);
        }
        System.out.println("The inorder output is : ");
        inorder(root);
        System.out.println();
        printInRange(root, 6, 10);
    }
}

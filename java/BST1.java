import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BST1 {
   static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){// constructor 
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public static Node insert(Node root , int val){
        if(root == null){
            root = new Node(val);//object construction
            return root;
        }
        if(root.data>val){
            //left subtree
            root.left = insert(root.left, val);
        }
        else{
            //right subtree
            root.right = insert(root.right, val);
        }
        return root;
    }

    // printing inorder traversal of the tree 
    public static void inorder(Node root){
        if(root == null){
            return;
        }inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    // printing level order tree
     public static void levelOrder(Node root){
            if(root == null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node currnode = q.remove();
              if(currnode == null){
                System.out.println();//null ate hi next line 
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
              }else{
                System.out.print(currnode.data+" ");
                if(currnode.left != null){
                    q.add(currnode.left);
                }
                if(currnode.right != null){
                    q.add(currnode.right);
                }
            }
            }
        }
        // Visual tree structure printer
public static void printTreeStructure(Node root, String indent, boolean isLeft) {
    if (root == null) return;

    System.out.print(indent);
    if (isLeft) {
        System.out.print("├── ");
        indent += "│   ";
    } else {
        System.out.print("└── ");
        indent += "    ";
    }
    System.out.println(root.data);

    printTreeStructure(root.left, indent, true);
    printTreeStructure(root.right, indent, false);
}
public static boolean search(Node root , int key){
    if(root == null){
        return false;
    }
    if(root.data>key){//left subtree searching 
     return search(root.left, key);
    }else if(root.data == key ){
        return true;
    }else{
        return search(root.right , key);
    }
}
    public static Node delete(Node root, int val){
        //searching for the node
        if(root.data>val){//left subtree searching
        root.left = delete(root.left, val);   
        }else if ( root.data< val ){//right subtree searching 
         root.right = delete(root.right , val);   
        }//when reached on the root , delete that Node
        else{
          // case1 : leaf node 
          if(root.left == null && root.right == null){
            return null;
          }
          // case2 : one child 
          if(root.left == null){
            return root.right;
          }else if( root.right == null){
            return root.left;
          }
          //case3: have both the children
          Node IS = inorderSuccessor(root.right);
          root.data = IS.data;
          root.right = delete(root.right, IS.data);
        }
        return root;
    }
    public static Node inorderSuccessor(Node root){
    while(root.left != null){
        root = root.left;
      }
        return root;
    }
  public static void main(String[] args) {
    int values[]= {1,5,6,3,4,7,2};
    Node root = null;

    for(int i = 0; i<=values.length-1;i++){
     root = insert(root, values[i]);
    }
    System.out.println("The inorder output : ");
    inorder(root);
    System.out.println();
    System.out.println("The level order output : ");
    levelOrder(root);
    System.out.println();
    System.out.println("Visual Tree Structure:");
    printTreeStructure(root, "", false);
       System.out.println();
       Scanner sc = new Scanner(System.in);
       System.out.print("Enter the key you want to search : ");
       int key = sc.nextInt();
      
       if(search(root, key)){
        System.out.println("The key is found");
       }else{
        System.out.println("The key is not found");
        System.out.println();
       }
       System.out.print("Enter the data you want to delete: ");
       int m = sc.nextInt();
      root = delete(root, m);
      printTreeStructure(root, "", false);
      sc.close();
  }    
}

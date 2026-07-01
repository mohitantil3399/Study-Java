import java.util.ArrayList;

public class BST3 {

   static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public static Node insert(Node root , int val){
       if(root == null){
        root = new Node(val);//object 
        return root;
       }else if (root.data >val){
        //left subtree
         root.left = insert(root.left, val);
         
       }else{
        //right subtree
        root.right = insert(root.right, val);
        
       }
       return root;
    }
    public static void printP(ArrayList<Integer> path ){
       int i = 0;
            while(i<=path.size()-1){ 
            System.out.print(path.get(i));
            if(i<path.size()-1){
                System.out.print("->");
            }
            i++;
        }System.out.println();
    }
    public static void printPath(Node root , ArrayList<Integer> path){
        if(root == null){
            return;
        }// add root data to list path 
        path.add(root.data);
        if(root.left == null & root.right == null){
            // condition for leaf node, and printing the list path  
            printP(path);
        }
        else{// else will keep adding 
        printPath(root.left, path);
        printPath(root.right, path);
        }
        path.remove(path.size()-1);
         
    }
    public static void main(String[]args){
     int value[] = {8,5,3,6,10,11,14};
     Node root = null;
     for(int i = 0; i < value.length;i++){
        root = insert(root, value[i]);
     }// as we are creating a new arraylist , so passing a new arraylist in the initialization 
     printPath(root, new ArrayList<>());
    }
}

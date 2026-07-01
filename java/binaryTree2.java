import java.util.Queue;
import java.util.LinkedList;
public class binaryTree2{

        static class Node{
            int data;
            Node left;
            Node right;
            Node (int data){
                this.data = data;
                this.left = null;
                this.right = null ;
            }
        }
        
        public static class BinaryTree{
            static int idx = -1;
            public static Node buildTree(int node[]){
                idx++;
              // base case 
              if(node[idx] ==-1){
                return null;
              }
              Node newnode = new Node(node[idx]);
              newnode.left = buildTree(node);
              newnode.right = buildTree(node);
              return newnode;
            }
        }
        public static void preorder(Node root){
            if(root == null){
                return;
            }
            System.out.print(root.data+"  ");
            preorder(root.left);
            preorder(root.right);
        }
        public static void inorder(Node root){
            if(root == null){
                return;
            }inorder(root.left);
            System.out.print(root.data+"  ");
            inorder(root.right);
        }
        public static void postorder(Node root){
            if(root == null){
                return ;
            }postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+"  ");
        }
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
            public static int countOfNodes(Node root){
                if(root == null ){
                    return 0;
                }int leftcount = countOfNodes(root.left);
                int rightcount = countOfNodes(root.right);
                return leftcount+rightcount+1;// +1 for the rootnode itself 
            }
            public static int sumOfNodes(Node root ){
                if(root == null){
                    return 0;
                }int leftsum = sumOfNodes(root.left);
                int rightsum = sumOfNodes(root.right);
                return leftsum + rightsum +root.data; // we are finding the sum of data at the node 
            }
            public static int heightOfTree(Node root ){
                if(root == null){
                    return 0;
                }int leftheight = heightOfTree(root.left);
                int rightheight = heightOfTree(root.right);
                int height = Math.max(leftheight,rightheight);
                return height + 1;// +1 for root level to be added

            }
       static class TreeInfo{
        int height;
        int diameter;
        TreeInfo(int height,int diameter){//constructor
            this.height = height;
            this.diameter = diameter;
        }
        public static TreeInfo diameter1(Node root){//method
            if(root == null){
              return new TreeInfo(0,0 );
            }
         TreeInfo left = diameter1(root.left);
         TreeInfo right = diameter1(root.right);

         int height = Math.max(left.height,right.height)+1;// you are using objects , so define property you are refering to 
         int diam1 = left.diameter;
         int diam2 = right.diameter ;
         int diam3 = left.height + right.height + 1;

         int diam = Math.max(Math.max(diam1,diam2),diam3);
         TreeInfo info = new TreeInfo(height, diam);
         return info;
        }  
       }
           
    public static void main(String[] args) {
        // input data for printing nodes of the tree: 
        int node[]= {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        // constructor of BinaryTree class is tree :
        BinaryTree tree = new BinaryTree();
        // object of the class is root, 
        Node root = tree.buildTree(node);// only prints root of the tree, that is the starting index only
       System.out.println("The preorder output (root -> left -> right): ");
        preorder(root);
        System.out.println();
        System.out.println();
        System.out.println("The inorder output (left -> root -> right ): ");
        inorder(root);
        System.out.println();
        System.out.println();
        System.out.println("The post order output (left->right->root): ");
        postorder(root);
        System.out.println();
        System.out.println();
        System.out.println("The level order output(is like a real tree ) : ");
        levelOrder(root);
        System.out.println();
       System.out.println("The total number of Nodes in the Binarytree are : ");
       System.out.println(countOfNodes(root));// a separate call to print is needed for the functions returns an integer , does not print it 
        System.out.println();
       System.out.println("The total sum of data in Nodes in the Binarytree is : ");
       System.out.println(sumOfNodes(root));// a separate call to print is needed for the functions returns an integer , does not print it 
       System.out.println();
       System.out.println("The height of the BinaryTree is : ");
        System.out.println(heightOfTree(root));
        System.out.println();
        System.out.println("The diameter of the BinaryTree is :");
        System.out.println(TreeInfo.diameter1(root).diameter);
        
}
}

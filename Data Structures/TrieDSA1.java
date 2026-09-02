public class TrieDSA1 {

    static class Node {
       Node[] children;
       boolean endOfWord;
       public Node(){//constructor of node class
         children = new Node[26];//declaring size of children array
         // for memory allocation , we need children array to be created
         // but we want not to store any initial information , so initialising with null 
         for(int i =0;i<26;i++){
            children[i] = null;
         }
         endOfWord = false;// initialized with false 
       }
    }
    static Node root = new Node();// create a root 
    public static void insert(String word ){
          Node curr = root;// for our root is static we dont want to change it again and again , create a cuurnode ,int he name of root , to be updated every then on each step
        for(int i = 0; i<word.length();i++){// time complexity = O(word.legth())
           int idx = word.charAt(i)-'a';
           if(curr.children[idx]== null){
            //creating a new node 
            curr.children[idx] = new Node();
           }//updating value of root
           if(i == word.length()-1){
            curr.children[idx].endOfWord = true ;
           }
           curr = curr.children[idx];
        }
    }
    public static boolean search(String key ){
        Node curr = root;
        for(int i =0; i <key.length();i++){
            int idx = key.charAt(i)-'a';
            //finding the node 
            Node node = curr.children[idx];
            if(node == null){
                return false;
            }if(i==key.length()-1 && node.endOfWord == false){
                return false ;
            }//updating level , that is the value of node
            curr = curr.children[idx];
        }
        return true;
    }
  public static void main(String[] args) {
    String words[] = {"the","there","a","any","their"};
    for(int j =0;j<words.length;j++){
        insert(words[j]);
    }
    System.out.println(search("thor"));
    System.out.println(search("there"));
    System.out.println(search("an"));
    
  }    
}

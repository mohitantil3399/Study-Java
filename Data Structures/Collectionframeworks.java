import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
public class Collectionframeworks {
    public static void main(String[]args){
        Queue<String> que = new LinkedList<String>() ;
        List<String> list = new ArrayList<String>();

        String sentence = "This monsoon has come with heavy rainfall and chilling wind.";
        String[] chunks = sentence.split(" "); // split by spaces

        for (String chunk : chunks) {
            que.add(chunk);
        }
    
        for (String q : que) {
            System.out.println(q);
        }
    }
}

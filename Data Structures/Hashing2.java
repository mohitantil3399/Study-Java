
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Hashing2 {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1, "ashish");
        map.put(2, "abhishekh");
        map.put(3, "abhishekh");
        map.put(4, "arshit");
        map.put(5, "Hardik");
        System.out.println(map);
        // for removing , pass the key 
        map.remove(3);
        System.out.println(map);
        // . containskey is a boolean type function 
        System.out.println(map.containsKey(3));
        System.out.println(map.get(4));
        map.put(3, "Pande");
        // direct for loop to iterate 
        for(Map.Entry<Integer,String> e : map.entrySet()){
          System.out.print(e.getKey());
          System.out.println(" "+e.getValue());
        }System.out.println();
         // iteration through sets 
         Set<Integer> keys = map.keySet();
         for(int key : keys ){
            System.out.println(key + " "+ map.get(key));
         }System.out.println();
    }
}

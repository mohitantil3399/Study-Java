import java.util.ArrayList;
import java.util.Comparator;
/**
 * ArrayList (array-backed list) — a resizable array implementation for storing object
 * references on the heap. Elements are stored in a contiguous backing array which is
 * resized (copied to a larger array) when capacity is exceeded. Typical operations:
 *
 * - add(element): amortized O(1) when appending to the end (resizing occasionally costs O(n))
 * - add(index, element): O(n) for inserting at an arbitrary position (shifts elements)
 * - get(index): O(1) random access by index
 * - set(index, element): O(1) update at index
 * - remove(index): O(n) for removing at an arbitrary position (shifts elements)
 * - contains / indexOf: O(n) linear search
 * - iteration over all elements: O(n)
 *
 * Compared to linked lists, ArrayList provides fast random access (O(1) get/set)
 * but slower insertions/removals at arbitrary positions due to element shifting.
 * ArrayList is not synchronized by default; use external synchronization if needed.
 */
public class ArraylistsJava {
    public static void main(String[]args){
        // making an object of ArrayList class with specified type as Integer class 
        ArrayList<Character> list = new ArrayList<Character>();

        //check if the list is initialized 
        System.out.println(list);

        //add function appends to the next index after last element 
        for (char c=48;c<100;c++){
            list.add(c);
        }
        System.out.println(list);
//sorting function on the list does not return the list 
        list.sort(Comparator.reverseOrder());
        System.out.println("\n\n"+list);
        // adding an element to a given index 
        list.add(9, '*');
        System.out.println("\n\n"+list);

        //to get an element at some index 
        char element = list.get(9);
        System.out.println("Element at given index: "+element);

        //to modify any element at any index 
        list.set(3, null);
        //size of list 
        int size = list.size();
        System.out.println("list size: "+size);
        //can use remove function to remove an index 
        list.remove(9);
        System.out.println(list);
    }
}

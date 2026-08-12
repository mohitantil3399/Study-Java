import java.util.*;

/**
 * ============================================================================
 *                    JAVA COLLECTIONS FRAMEWORK (JCF) NOTES
 * ============================================================================
 * 
 * 1. WHAT IS JAVA COLLECTIONS FRAMEWORK?
 *    - A unified architecture for storing and manipulating a group of objects.
 *    - Located in the `java.util` package.
 * 
 * 2. HIERARCHY OVERVIEW:
 * 
 *               Iterable (Interface)
 *                  |
 *              Collection (Interface)
 *         /        |                 \
 *    List (I)    Set (I)           Queue (I) / Deque (I)
 *     / | \       / | \              /      \
 *    AL LL Vec  HS LHS TS          LL    PriorityQueue / ArrayDeque
 * 
 *    Note: Map (Key-Value pairs) is NOT a sub-interface of Collection,
 *          but is part of the Java Collections Framework.
 *          Map -> HashMap, LinkedHashMap, TreeMap, ConcurrentHashMap.
 * 
 * ============================================================================
 * 3. KEY INTERFACES & IMPLEMENTATIONS:
 * 
 * [A] LIST INTERFACE (Ordered, Allows Duplicates, Index-based Access):
 *     - ArrayList: Resizable array. Fast random access O(1), slow insert/delete O(n).
 *     - LinkedList: Doubly linked list. Fast insert/delete O(1) at ends, O(n) index access.
 *     - Vector: Synchronized (Thread-safe) resizable array (Legacy).
 *     - Stack: Subclass of Vector implementing LIFO (Last-In-First-Out).
 * 
 * [B] QUEUE & DEQUE INTERFACE (FIFO / Priority / Double-Ended):
 *     - Queue: FIFO (First-In-First-Out). Methods: add/offer, remove/poll, element/peek.
 *     - LinkedList: Implements both List and Queue/Deque.
 *     - PriorityQueue: Elements ordered by natural order or Comparator (Min-Heap / Max-Heap).
 *     - ArrayDeque: Resizable array implementation of Deque (Faster than Stack/LinkedList).
 * 
 * [C] SET INTERFACE (Unordered / Ordered, Unique Elements Only):
 *     - HashSet: Uses HashMap internally. Fast O(1) ops, no order guarantee.
 *     - LinkedHashSet: Maintains insertion order of elements.
 *     - TreeSet: Red-Black Tree implementation. Stores elements in sorted order O(log n).
 * 
 * [D] MAP INTERFACE (Key-Value Pairs, Keys must be Unique):
 *     - HashMap: Hash table based, O(1) avg ops, allows 1 null key. Unordered.
 *     - LinkedHashMap: Maintains insertion order of key-value pairs.
 *     - TreeMap: Red-Black Tree based, keys sorted naturally or via Comparator, O(log n).
 * ============================================================================
 */
public class Collectionframeworks {

    public static void main(String[] args) {
        System.out.println("====== 1. QUEUE DEMO (Sentence Splitting) ======");
        demoQueue();

        System.out.println("\n====== 2. LIST DEMO (ArrayList & LinkedList) ======");
        demoList();

        System.out.println("\n====== 3. PRIORITY QUEUE DEMO (Min-Heap / Max-Heap) ======");
        demoPriorityQueue();

        System.out.println("\n====== 4. SET DEMO (HashSet vs TreeSet) ======");
        demoSet();

        System.out.println("\n====== 5. MAP DEMO (HashMap & TreeMap) ======");
        demoMap();

        System.out.println("\n====== 6. UTILITY METHODS (Collections Class) ======");
        demoCollectionsUtility();
    }

    // --- 1. Queue Demo ---
    public static void demoQueue() {
        Queue<String> que = new LinkedList<>();
        String sentence = "This monsoon has come with heavy rainfall and chilling wind.";
        String[] chunks = sentence.split(" "); // split by spaces

        for (String chunk : chunks) {
            que.add(chunk);
        }

        System.out.println("Processing Queue (FIFO):");
        while (!que.isEmpty()) {
            System.out.print(que.poll() + " -> "); // poll() removes and returns head
        }
        System.out.println("END");
    }

    // --- 2. List Demo ---
    public static void demoList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("C++");
        arrayList.add("Java"); // Allows duplicates

        System.out.println("ArrayList (Preserves insertion order): " + arrayList);
        System.out.println("Element at index 1: " + arrayList.get(1));

        LinkedList<String> linkedList = new LinkedList<>(arrayList);
        linkedList.addFirst("JavaScript");
        linkedList.addLast("Go");
        System.out.println("LinkedList with First/Last additions: " + linkedList);
    }

    // --- 3. PriorityQueue Demo ---
    public static void demoPriorityQueue() {
        // Default PriorityQueue is a Min-Heap (smallest numbers come first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(40);
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);

        System.out.print("Min-Heap PriorityQueue Polling: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // Prints: 10 20 30 40
        }
        System.out.println();

        // Max-Heap PriorityQueue (largest numbers come first)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(40, 10, 30, 20));

        System.out.print("Max-Heap PriorityQueue Polling: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // Prints: 40 30 20 10
        }
        System.out.println();
    }

    // --- 4. Set Demo ---
    public static void demoSet() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Banana");
        hashSet.add("Apple");
        hashSet.add("Mango");
        hashSet.add("Apple"); // Duplicate ignored

        System.out.println("HashSet (Unique, Unordered): " + hashSet);

        Set<String> treeSet = new TreeSet<>(hashSet);
        System.out.println("TreeSet (Unique, Automatically Sorted): " + treeSet);
    }

    // --- 5. Map Demo ---
    public static void demoMap() {
        Map<String, Integer> studentMarks = new HashMap<>();
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 92);
        studentMarks.put("Charlie", 78);

        System.out.println("HashMap: " + studentMarks);
        System.out.println("Bob's mark: " + studentMarks.get("Bob"));

        // Iterating over HashMap
        System.out.println("Iterating Key-Value Pairs:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }

        // TreeMap keeps keys sorted
        Map<String, Integer> sortedMarks = new TreeMap<>(studentMarks);
        System.out.println("TreeMap (Sorted by key): " + sortedMarks);
    }

    // --- 6. Collections Utility Demo ---
    public static void demoCollectionsUtility() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7, 3, 2));

        System.out.println("Original List: " + numbers);

        Collections.sort(numbers);
        System.out.println("Sorted List: " + numbers);

        Collections.reverse(numbers);
        System.out.println("Reversed List: " + numbers);

        System.out.println("Max Element: " + Collections.max(numbers));
        System.out.println("Min Element: " + Collections.min(numbers));
        System.out.println("Frequency of 2: " + Collections.frequency(numbers, 2));
    }
}

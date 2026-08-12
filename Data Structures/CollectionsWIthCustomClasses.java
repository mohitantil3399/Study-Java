import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * =========================================================================================================
 *                          INTERFACES VS CLASSES & CUSTOM COLLECTIONS DEMO
 * =========================================================================================================
 *
 * 1. DIFFERENCE BETWEEN INTERFACES AND CLASSES IN JAVA:
 * ---------------------------------------------------------------------------------------------------------
 *  FEATURE         | CLASS                                      | INTERFACE
 * ---------------------------------------------------------------------------------------------------------
 *  Definition      | A blueprint for creating objects that      | A contract specifying WHAT behavior a class
 *                  | contains state (fields) and behavior.      | must implement (abstract methods).
 * ---------------------------------------------------------------------------------------------------------
 *  Instantiation   | Can be instantiated directly via `new`.    | CANNOT be instantiated directly.
 * ---------------------------------------------------------------------------------------------------------
 *  Implementation  | Contains state (fields) and concrete       | Declares method signatures (abstract, plus
 *                  | method implementations.                    | default/static methods since Java 8). No fields.
 * ---------------------------------------------------------------------------------------------------------
 *  Inheritance     | Single class inheritance (`extends`).      | Multiple interface inheritance (`implements`).
 * ---------------------------------------------------------------------------------------------------------
 *  JCF Example     | `HashSet`, `HashMap` are concrete classes   | `Set`, `Map` are interfaces defining the
 *                  | implementing the collection contracts.     | structural contracts for collections.
 * ---------------------------------------------------------------------------------------------------------
 *  Custom Example  | `MyCustomSet`, `MyCustomHashMap` are       | Can be referenced as interface types:
 *                  | user-written classes implementing Set/Map. | `Set<Student> s = new MyCustomSet<>();`
 * ---------------------------------------------------------------------------------------------------------
 *
 * 2. HASHSET & HASHMAP WITH CUSTOM CLASSES:
 *
 *  - How HashSet & HashMap Work Internally:
 *    HashSet uses HashMap internally (storing elements as Keys in a HashMap).
 *    HashMap uses the key's `hashCode()` to locate the target bucket in a Hash Table,
 *    and then uses `equals()` to check if an identical key already exists.
 *
 *  - The Mandatory Contract for Custom Classes:
 *    1. If `obj1.equals(obj2)` is TRUE, then `obj1.hashCode() == obj2.hashCode()` MUST be TRUE.
 *    2. If `obj1.hashCode() == obj2.hashCode()`, `obj1.equals(obj2)` MAY be true or false (Hash Collision).
 *    3. Always override BOTH `hashCode()` and `equals()` together!
 * =========================================================================================================
 */

// Custom Class with properly implemented hashCode() and equals()
class Student {

    private final int id;
    private final String name;

    //This class constructor to intialize the class objects properties.
    public Student(int id, String name) {

        this.id = id;
        this.name = name;
    }

    //Getter and setter of the private propeties of the objects , in kotlin we have data classes instead to avoid this boilerplate
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Overriding equals() to define logical equality based on student ID and Name
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // same reference check
        }
        if (o == null || getClass() != o.getClass()) {
            return false; // type check
        }
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name);
    }

    // Overriding hashCode() to generate consistent hash codes based on fields
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

// Custom Class WITHOUT hashCode() and equals() to demonstrate the problem
class BadStudent {

    private int id;
    private String name;

    public BadStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BadStudent{id=" + id + ", name='" + name + "'}";
    }
}

// ============================================================================
// SELF-WRITTEN CUSTOM CLASSES IMPLEMENTING THE COLLECTION INTERFACE CONTRACTS
// ============================================================================

/**
 * MyCustomSet: A self-written custom class implementing the Set interface contract.
 * Distinguishes self-written implementation from built-in HashSet.
 */
class MyCustomSet<E> extends AbstractSet<E> {
    private final List<E> elements = new ArrayList<>();

    @Override
    public boolean add(E e) {
        // Enforces Set contract: no duplicates allowed
        if (!elements.contains(e)) {
            elements.add(e);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }
}

/**
 * MyCustomHashMap: A self-written custom class implementing the Map interface contract.
 * Distinguishes self-written implementation from built-in HashMap.
 */
class MyCustomHashMap<K, V> extends AbstractMap<K, V> {
    private final List<Entry<K, V>> entryList = new ArrayList<>();

    @Override
    public V put(K key, V value) {
        // Enforces Map contract: update value if key exists, else add new entry
        for (Entry<K, V> entry : entryList) {
            if (Objects.equals(entry.getKey(), key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        entryList.add(new SimpleEntry<>(key, value));
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(entryList);
    }
}

public class CollectionsWIthCustomClasses {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" 1. DIFFERENCE BETWEEN INTERFACE AND CLASS");
        System.out.println(" (Built-in Classes vs Self-Written Classes)");
        System.out.println("==================================================");
        demoInterfaceVsClass();

        System.out.println("\n==================================================");
        System.out.println(" 2. HASHSET WITH CUSTOM CLASS (Student)");
        System.out.println("==================================================");
        demoHashSetWithCustomClass();

        System.out.println("\n==================================================");
        System.out.println(" 3. HASHMAP WITH CUSTOM CLASS AS KEY");
        System.out.println("==================================================");
        demoHashMapWithCustomClass();

        System.out.println("\n==================================================");
        System.out.println(" 4. WHY equals() & hashCode() ARE CRITICAL");
        System.out.println("==================================================");
        demoWhyHashCodeAndEqualsMatter();
    }

    private static void demoInterfaceVsClass() {
        // --- 1. BUILT-IN COLLECTION CLASSES IMPLEMENTING INTERFACES ---
        Set<Student> builtInSet = new HashSet<>();
        Map<Student, String> builtInMap = new HashMap<>();

        // --- 2. SELF-WRITTEN CUSTOM CLASSES IMPLEMENTING INTERFACES ---
        Set<Student> customSet = new MyCustomSet<>();
        Map<Student, String> customMap = new MyCustomHashMap<>();

        Student s1 = new Student(101, "Mohit");
        Student s2 = new Student(101, "Mohit"); // Duplicate student

        // Adding to Built-in HashSet
        builtInSet.add(s1);
        builtInSet.add(s2);

        // Adding to Self-Written MyCustomSet
        customSet.add(s1);
        customSet.add(s2);

        // Adding to Built-in HashMap & Self-Written MyCustomHashMap
        builtInMap.put(s1, "Grade A");
        customMap.put(s1, "Grade A");

        System.out.println("1. Interface: Set<Student>");
        System.out.println("   a) Built-in Implementation (HashSet): " + builtInSet.getClass().getName());
        System.out.println("      Size after duplicate check: " + builtInSet.size());
        System.out.println("   b) Self-Written Implementation (MyCustomSet): " + customSet.getClass().getName());
        System.out.println("      Size after duplicate check: " + customSet.size());

        System.out.println("\n2. Interface: Map<Student, String>");
        System.out.println("   a) Built-in Implementation (HashMap): " + builtInMap.getClass().getName());
        System.out.println("      Value for s1: " + builtInMap.get(s1));
        System.out.println("   b) Self-Written Implementation (MyCustomHashMap): " + customMap.getClass().getName());
        System.out.println("      Value for s1: " + customMap.get(s1));
    }

    private static void demoHashSetWithCustomClass() {
        // Set interface storing custom Student objects
        Set<Student> studentSet = new HashSet<>();

        Student s1 = new Student(101, "Mohit");
        Student s2 = new Student(102, "Rohan");
        Student s3 = new Student(101, "Mohit"); // Duplicate data!

        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3); // HashSet uses equals() & hashCode() to detect duplicate and reject it

        System.out.println("HashSet size (Expected: 2): " + studentSet.size());
        System.out.println("HashSet contents:");
        for (Student s : studentSet) {
            System.out.println(" - " + s);
        }
    }

    private static void demoHashMapWithCustomClass() {
        // Map interface storing custom Student objects as Keys
        Map<Student, String> studentGradeMap = new HashMap<>();

        Student s1 = new Student(101, "Mohit");
        Student s2 = new Student(102, "Rohan");

        studentGradeMap.put(s1, "Grade A");
        studentGradeMap.put(s2, "Grade B");

        System.out.println("HashMap entries:");
        for (Map.Entry<Student, String> entry : studentGradeMap.entrySet()) {
            System.out.println(" Key: " + entry.getKey() + " => Value: " + entry.getValue());
        }

        // Searching using a NEW object with the SAME field values
        Student searchStudent = new Student(101, "Mohit");
        System.out.println("\nLooking up student with ID 101:");
        if (studentGradeMap.containsKey(searchStudent)) {
            System.out.println(" Found grade: " + studentGradeMap.get(searchStudent));
        } else {
            System.out.println(" Not found!");
        }
    }

    private static void demoWhyHashCodeAndEqualsMatter() {
        // BadStudent does NOT override hashCode() or equals()
        Set<BadStudent> badSet = new HashSet<>();

        BadStudent b1 = new BadStudent(101, "Mohit");
        BadStudent b2 = new BadStudent(101, "Mohit"); // Identical fields

        badSet.add(b1);
        badSet.add(b2);

        System.out.println("Without equals() & hashCode() overridden:");
        System.out.println(" - Adding 2 logically duplicate BadStudents to HashSet:");
        System.out.println("   HashSet size: " + badSet.size() + " (FAIL: Contains duplicate entries!)");

        Map<BadStudent, String> badMap = new HashMap<>();
        badMap.put(b1, "Grade A");

        BadStudent searchBad = new BadStudent(101, "Mohit");
        System.out.println(" - Lookup in HashMap with a new BadStudent(101, 'Mohit'):");
        System.out.println("   Contains Key? " + badMap.containsKey(searchBad) + " (FAIL: Cannot find entry!)");
    }
}

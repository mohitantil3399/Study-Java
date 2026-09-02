
public class variables {

    //These are all instance varibales , declared in the class , and are all initialised with some sort of value 
    static String name = "Sunny";
    static char ch = 'a';
    static int buddy = 18;
    int simple[] = {1, 2};

    static String name(String n) {
        return n;
    }

    public static void main(String[] args) {
        //variables obj = new variables();
        String name = name("brother");//this method is non static , hence need an objective declaration to be accessed
        System.out.println(name);
        System.out.println("This one refers the instance variable: " + name);
        System.out.println(ch);

    }
}

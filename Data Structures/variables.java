public class variables {
    //These are all instance varibales , declared in the class , and are all initialised with some sort of value 
    static String name = "Sunny";
    static char ch = 'a';
    static int buddy = 18;
    int simple[]={1,2};

    static String name(String n){
        return n;
    }
    static void main(String[]args){
        //variables obj = new variables();
        String name = name("brother");//this method is non static , hence need an objective declaration to be accessed
        IO.println(name);
        IO.println("This one refers the instance variable: "+ name);
        IO.println(ch);
    
    }
}

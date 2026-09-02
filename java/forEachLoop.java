public class forEachLoop {
    public static void main(String[] args){
        String sentence[] = {"Let's"," test"," the"," for"," each loop in java"};
        for (String iterator : sentence) {
            System.out.println(iterator);
        }
            System.out.println("It only works over iterables like arrays.");
    }
}

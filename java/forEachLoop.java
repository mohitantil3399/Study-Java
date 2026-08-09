public class forEachLoop {
    void main(){
        String sentence[] = {"Let's"," test"," the"," for"," each loop in java"};
        for (String iterator : sentence) {
            IO.println(iterator);
        }
            System.out.println("It only works over iterables like arrays.");
    }
}

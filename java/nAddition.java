public class nAddition {
    private int add(int... numbers){
        int sum = 0;
        for (int num:numbers){
            sum +=num;
        }
        return sum;
    }
    void main(){
    int result1 = add(10,20,30,40);
    int result2 = add(10,20,30,40,50,60);
    IO.println("first result: "+result1);
    IO.println("second result: "+result2);
    }
}

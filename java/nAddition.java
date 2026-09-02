public class nAddition {
    private static int add(int... numbers){
        int sum = 0;
        for (int num:numbers){
            sum +=num;
        }
        return sum;
    }
    public static void main(String[] args){
        int result1 = add(10,20,30,40);
        int result2 = add(10,20,30,40,50,60);
        System.out.println("first result: "+result1);
        System.out.println("second result: "+result2);
    }
}

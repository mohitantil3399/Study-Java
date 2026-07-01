public class SumEven {
     public static int sumEven(int m ){
        if (m < 0 ){
            System.out.println("Enter a number greater than 0");
            return 0;
        }
        int sum = 0;
        int k = 0;
        while(k <= m){
          if(k%2==0){
             sum += k ;
          }
          k++;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println("The sum is : "+sumEven(100));
    }
}

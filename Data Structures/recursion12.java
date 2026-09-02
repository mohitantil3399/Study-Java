import java.util.Scanner;
public class recursion12 {
 public static void printPermutation( String str , String permutations){
          if ( str.length()==0 ){
            System.out.println(permutations);
            return;
          }
      for ( int i = 0; i <str.length();i++){
        char currChar = str.charAt(i);
        //str - charAt(i);that means the charcter is subtracted from the string 
        //making new string according the above mentioned condition 
        String newStr = str.substring(0, i) + str.substring(i+1);//no need of ending index because its assumed to be the last one by default .  
        //index i is not included therfore only the characters before the index i are added in substring 
          printPermutation(newStr, permutations+currChar);
      }
    } public static int factorial(int n){
        if ( n ==0 || n==1){
          return 1;
         } else{
          return n*factorial(n-1);
          
      }
    }
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in );
      System.out.print("Enter the string : ");
        String str = sc.nextLine();//declaration of string
        System.out.println("The total number of characters int the string "+str+" are : "+str.length());
        int count = factorial(str.length());
        System.out.println("The total number of permutations are: "+count); 
        printPermutation(str, "");
        sc.close();
    }
}

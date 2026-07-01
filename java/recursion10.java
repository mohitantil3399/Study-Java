import java.util.Scanner;
import java.util.HashSet;
public class recursion10 {
    public static int count = 0;
    public static void uniqueSubsequences(String str , int idx , String NewString ,HashSet<String> set){
        if(idx == str.length()){
         if (set.contains(NewString) ){
          return;
         }else{
            System.out.println(NewString);
            set.add(NewString);
            count++;
            return;
         }
        }
        char currchar = str.charAt(idx);
        //if character is chosen
        uniqueSubsequences(str, idx+1, NewString+currchar ,set);
        //if not chosen 
        uniqueSubsequences(str, idx+1, NewString,set);
        
    }public static void main(String[] args) {
        Scanner ds = new Scanner(System .in);
        System.out.print("Eter the string : ");
        String str = ds.nextLine();
        HashSet<String> set = new HashSet<>();
        uniqueSubsequences(str, 0, "",set);
        System.out.println("The total number of uniques subsequences is :  "+count);
        ds.close();
    }
}
